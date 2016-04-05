<portfolios>
{
for $detail in doc("data.xml")/list/com.fimt.cbs.ccs.model.ProjectedCashDetailInfo
let $portfolioId := $detail/portfolioId

return <portfolio>{$portfolioId}<multiCurrencyDetails>
	{
		for $multcur in $detail//com.fimt.cbs.ccs.model.ProjectedCashDetailMCInfo
		let #$ns = $multcur//com.fimt.cbs.ccs.model.ProjectedCashDetailMCInfo/node()
		return <currencyDetail>{$multcur/localCurrencyCode}</currencyDetail>
		{
			
		}
	}</multiCurrencyDetails>
	</portfolio>
}
</portfolios>