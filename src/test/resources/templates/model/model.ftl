public class ${className} implements Serializable {
    private static final long serialVersionUID = 1L;

<#if columns?exists && columns?size gt 0>
<#list columns as column >
    /**
    * ${column.remarks}
    */
    private ${column.dataType} ${column.attributeName};

</#list>
</#if>

<#if columns?exists && columns?size gt 0>
<#list columns as column >
    public ${column.dataType} get${column.attributeName?cap_first}() {
        return ${column.attributeName};
    }

    public void set${column.attributeName?cap_first}(${column.dataType} ${column.attributeName}) {
        this.${column.attributeName} = ${column.attributeName};
    }

</#list>
</#if>
}
