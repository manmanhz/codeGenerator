package ${dataModal.packageName};

<#if (dataModal.extendClass)??>
import ${dataModal.extendClassNamespace};
</#if>
import ${dataModal.modalClassNamespace};

public interface ${dataModal.className} <#if (dataModal.extendClass)??>extends ${dataModal.extendClassName}<${dataModal.modalClassSimpleName}></#if> {

}

