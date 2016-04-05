<portfolios>
{
for $detail in doc(
    "data.xml"
  )/list/com.fimt.cbs.ccs.model.ProjectedCashDetailInfo
let $portfolioId := $detail/portfolioId
return <portfolio>{
      $portfolioId
    }</portfolio>
  }
</portfolios>