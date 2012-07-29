<#--
表格列标签：展示数据列。
	title：标题（列头）。直接显示字符串。默认""。
	code：标题（列头）。显示国际化信息。默认""。
	width：列宽。默认""。
	align：对齐方式。
	class：css样式class
	style：css样式style
-->
<#macro column title="" code="" width="" align="" class="" style="">
<#if title="" && code=""><td>title and code all not assign!</td><#return></#if>
<#if i==-1>
	<th<#if width!=""> width="${width}"</#if><#if class!=""> class="${class}"</#if><#if style!=""> style="${style}"</#if>><#if title!="">${title}<#else><@s.mt code=code text=code/></#if></th><#rt/>
<#else>
	<td<#if align!=""> align="${align}"</#if><#if class!=""> class="${class}"</#if><#if style!=""> style="${style}"</#if>><#nested/></td><#rt/>
</#if>
</#macro>