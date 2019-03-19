package ${dataModal.packageName};

import org.springframework.stereotype.Repository;

<#if (dataModal.extendClass)??>
import ${dataModal.extendClassNamespace};
</#if>
<#if (dataModal.implementsClassName)??>
import ${dataModal.implementsClassNamespace};
</#if>
import ${dataModal.modalClassNamespace};

@Repository
public class ${dataModal.className} <#if (dataModal.extendClass)??>extends ${dataModal.extendClassName}<${dataModal.modalClassSimpleName}></#if><#if (dataModal.implementsClassName)??> implements ${dataModal.implementsClassName}</#if> {

}

