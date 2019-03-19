    <update id="update">
      update ${dataModal.tableName}
      <include refid="updateSetSql" />
      where id=${"#\{"}id${"}"} and lock_version=${"#\{"}lockVersion${"}"}
    </update>

    <sql id="updateSetSql">
      <set>
      <#if dataModal.sqlColumns?size != 0 >
        <#assign sqlColumnsSize = dataModal.sqlColumns?size />
        <#list dataModal.sqlColumns as sqlColumn>
          <#assign i = sqlColumn_index />
        <#--?no_esc Disabling auto escaping-->
          <if test="${(dataModal.conditions[i])?no_esc}">
          <!-- ${i} -->${sqlColumn} = ${"#\{"}${dataModal.javaFields[i]}${"}"}<#if sqlColumn_index < sqlColumnsSize-1>,</#if>
          </if>
        </#list>
      </#if>
      </set>
    </sql>

