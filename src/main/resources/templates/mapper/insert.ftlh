    <insert id="insert">
      <selectKey keyProperty="id" resultType="long" order="BEFORE">
        select ${dataModal.sequenceName}.nextval from dual
      </selectKey>
      insert into ${dataModal.tableName}(
      <#if dataModal.sqlColumns?size != 0 >
        <#assign sqlColumnsSize = dataModal.sqlColumns?size />
        <#list dataModal.sqlColumns as sqlColumn>
        <!-- ${sqlColumn_index} -->${sqlColumn}<#if sqlColumn_index < sqlColumnsSize-1>,</#if>
        </#list>
      </#if>
      )
      values(
    <#if dataModal.javaFields?size != 0 >
      <#assign javaFieldsSize = dataModal.javaFields?size />
      <#list dataModal.javaFields as javaField>
        <!-- ${javaField_index} -->${"#\{"}${javaField}${"}"}<#if javaField_index < javaFieldsSize-1>,</#if>
      </#list>
    </#if>
      )
    </insert>


    <insert id="batchInsert">
      insert into wd_abnormal_fines_audit(
      <#if dataModal.sqlColumns?size != 0 >
        <#assign sqlColumnsSize = dataModal.sqlColumns?size />
        <#list dataModal.sqlColumns as sqlColumn>
          <!-- ${sqlColumn_index} -->${sqlColumn}<#if sqlColumn_index < sqlColumnsSize-1>,</#if>
        </#list>
      </#if>
      )
      SELECT ${dataModal.sequenceName}.NEXTVAL,A.* FROM (
      <foreach collection="list" item="item" index="index" separator="union all">
        SELECT
        <#if dataModal.javaFields?size != 0 >
          <#assign javaFieldsSize = dataModal.javaFields?size />
          <#list dataModal.javaFields as javaField>
            <!-- ${javaField_index} -->${"#\{"}${javaField}${"}"}<#if javaField_index < javaFieldsSize-1>,</#if>
          </#list>
        </#if>
        FROM DUAL
      </foreach>
      ) A
    </insert>
