$XSecondsFromNow = (Get-Date).AddSeconds(4)
$tInicial = 0

while((Get-Date) -lt $XSecondsFromNow){
  sleep 1
  Write-Host ("El proceso lleva ejecutandose: "+ ++$tInicial  +" Segundos")
}