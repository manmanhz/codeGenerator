package ${dataModal.packageName};

<#if dataModal.importClassNames?size != 0>
  <#list dataModal.importClassNames as importClassName >
import ${importClassName};
  </#list>
</#if>

<#if dataModal.annotations?size != 0>
  <#list dataModal.annotations as annotation >
@${annotation}
  </#list>
</#if>
public class ${dataModal.classSimpleName} <#if (dataModal.extendClassSimpleName)??>extends ${dataModal.extendClassSimpleName}<#if (dataModal.extendGenericClassSimpleName)??><${dataModal.extendGenericClassSimpleName}></#if></#if><#if dataModal.implementsClassSimpleNames?size != 0> implements ${dataModal.implementsClassSimpleNamesStr}</#if> {

<#list dataModal.fields as field>
    private ${field.fieldClassSimpleName} ${field.fieldName};
</#list>
}

