<ul>{
for $product in doc("catalog.xml")/catalog/product
where $product/@dept='ACC'
order by $product/name
return <li>{$product/name}</li>
}</ul>