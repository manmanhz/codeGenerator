package ${dataModal.packageName};

<#--需要注入的import数据-->
import lombok.Data;
<#if dataModal.importValues?size != 0>
  <#list dataModal.importValues as importValue >
import ${importValue};
  </#list>
</#if>

<#if (dataModal.extendClass)??>
import ${dataModal.extendClassNamespace};
</#if>

@Data
public class ${dataModal.className} <#if (dataModal.extendClass)??>extends ${dataModal.extendClassName}</#if> {

<#--需要注入的域变量数据 -->
<#list dataModal.fieldValues as fieldValue>
private ${fieldValue.fieldType} ${fieldValue.fieldName};
</#list>

}

