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
public interface ${dataModal.classSimpleName} <#if (dataModal.extendClassSimpleName)??>extends ${dataModal.extendClassSimpleName}<#if (dataModal.extendGenericClassSimpleName)??><${dataModal.extendGenericClassSimpleName}></#if></#if> {

<#if dataModal.methods?size != 0>
  <#list dataModal.methods as method>
    ${method.returnClassSimpleName} ${method.methodName}(${method.parametersStr});

  </#list>
</#if>
}

