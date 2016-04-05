<portfolios>
{
for $detail in doc(
    "data.xml"
  )/list/com.fimt.cbs.ccs.model.ProjectedCashDetailInfo
let $portfolioId := $detail/portfolioId

return <portfolio>{
    $portfolioId
  }<multiCurrencyDetails>
	{
		for $multcur in $detail//com.fimt.cbs.ccs.model.ProjectedCashDetailMCInfo
    let $amts := $multcur//*[requestedAmount]
		return <detail currency_code="{$multcur/localCurrencyCode}" >{
      for $amt in $amts
        return $amt/element()
    }</detail>
	}</multiCurrencyDetails>
	</portfolio>
}
</portfolios>