for $product in doc("catalog.xml")/catalog/product
let $name := $product/name
where $product/@dept = "ACC"
order by $name
return $name