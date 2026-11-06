param(
    [switch]$SkipBuild
)

$ErrorActionPreference = 'Stop'
Add-Type -AssemblyName System.Web.Extensions
$jsonParser = New-Object System.Web.Script.Serialization.JavaScriptSerializer
$jsonParser.MaxJsonLength = [int]::MaxValue
$projectRoot = Split-Path -Parent $PSScriptRoot
$blocksSource = Join-Path $projectRoot 'common/src/main/java/com/terraformersmc/cinderscapes/init/CinderscapesBlocks.java'
$itemsSource = Join-Path $projectRoot 'common/src/main/java/com/terraformersmc/cinderscapes/init/CinderscapesItems.java'
$assetRoot = Join-Path $projectRoot 'client/src/main/resources/assets/cinderscapes'
$generatedRoot = Join-Path $projectRoot 'common/src/main/generated/data/cinderscapes'

$failures = [System.Collections.Generic.List[string]]::new()

Get-ChildItem $projectRoot -Recurse -Filter '*.json' |
    Where-Object { $_.FullName -notmatch '\\(build|\.gradle|run)\\' } |
    ForEach-Object {
        $jsonPath = $_.FullName
        try { $jsonParser.DeserializeObject((Get-Content -LiteralPath $jsonPath -Raw)) | Out-Null }
        catch { $failures.Add("Invalid JSON: ${jsonPath}: $($_.Exception.Message)") }
    }

$blockText = Get-Content -LiteralPath $blocksSource -Raw
$blockNames = [regex]::Matches($blockText, 'register\("([^"]+)"') |
    ForEach-Object { $_.Groups[1].Value } | Sort-Object -Unique
foreach ($name in $blockNames) {
    $blockState = Join-Path $assetRoot "blockstates/$name.json"
    if (-not (Test-Path -LiteralPath $blockState)) { $failures.Add("Missing blockstate: $name") }

    if ($name -notmatch '^potted_' -and $name -notmatch 'wall_(hanging_)?sign$') {
        $lootPlural = Join-Path $generatedRoot "loot_tables/blocks/$name.json"
        $lootSingular = Join-Path $generatedRoot "loot_table/blocks/$name.json"
        if (-not (Test-Path -LiteralPath $lootPlural) -and -not (Test-Path -LiteralPath $lootSingular)) {
            $failures.Add("Missing loot table: $name")
        }
    }
}

$itemText = Get-Content -LiteralPath $itemsSource -Raw
$itemNames = [regex]::Matches($itemText, 'register(?:BlockItem)?\("([^"]+)"') |
    ForEach-Object { $_.Groups[1].Value } | Sort-Object -Unique
foreach ($name in $itemNames) {
    $model = Join-Path $assetRoot "models/item/$name.json"
    if (-not (Test-Path -LiteralPath $model)) { $failures.Add("Missing item model: $name") }
}

if (-not $SkipBuild) {
    & (Join-Path $projectRoot 'gradlew.bat') build --no-daemon --console=plain
    if ($LASTEXITCODE -ne 0) { $failures.Add("Gradle build failed with exit code $LASTEXITCODE") }
}

if ($failures.Count -gt 0) {
    $failures | ForEach-Object { Write-Error $_ }
    exit 1
}

Write-Host "Resource contract passed: $($blockNames.Count) blocks, $($itemNames.Count) items, all JSON files valid."
