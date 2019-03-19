    <select id="find" resultType="${dataModal.parameterAliasBo}">
      select * from ${dataModal.tableName} where id = ${"#\{"}id${"}"}
    </select>

    <select id="findVo" resultType="${dataModal.parameterAliasVo}">
      select *
      from ${dataModal.tableName} t
      where
      t.id = ${"#\{"}id${"}"}
    </select>

    <select id="selectBySo" resultType="${dataModal.parameterAliasBo}">
      select t.* from ${dataModal.tableName} t
      <include refid="soSqlFilter"/>
    </select>

    <select id="selectVoBySo" resultType="${dataModal.parameterAliasVo}">
      SELECT *
      FROM ${dataModal.tableName} t
      <include refid="soSqlFilter"/>
      order by t.CREATED_TIME desc
    </select>

    <select id="selectCountBySo" resultType="int">
      select count(1) from ${dataModal.tableName} t
      <include refid="soSqlFilter"/>
    </select>

    <delete id="deleteByIds" parameterType="map">
      delete from ${dataModal.tableName} t where t.id in
      <foreach collection="list" index="dex" item="item" open="("
               close=")" separator=",">
          ${"#\{"}item${"}"}
      </foreach>
    </delete>

    <delete id="deleteByIdList">
      delete from ${dataModal.tableName} t where t.id in
      <foreach collection="list" index="dex" item="item" open="("
               close=")" separator=",">
          ${"#\{"}item${"}"}
      </foreach>
    </delete>

    <select id="selectVoByIdList" resultType="${dataModal.parameterAliasVo}">
      select *
      from ${dataModal.tableName} t
      where t.id in
      <foreach collection="list" index="dex" item="item" open="("
               close=")" separator=",">
          ${"#\{"}item${"}"}
      </foreach>
    </select>

    <select id="selectByIdList" resultType="${dataModal.parameterAliasBo}">
      select * from ${dataModal.tableName} t where t.id in
      <foreach collection="list" index="dex" item="item" open="("
               close=")" separator=",">
          ${"#\{"}item${"}"}
      </foreach>
    </select>

    <sql id="soSqlFilter">
      <where>
      <#if dataModal.sqlColumns?size != 0 >
        <#assign sqlColumnsSize = dataModal.sqlColumns?size />
        <#list dataModal.sqlColumns as sqlColumn>
          <#assign i = sqlColumn_index />
        <#--?no_esc Disabling auto escaping-->
          <if test="${(dataModal.conditions[i])?no_esc}">
            and ${sqlColumn} = ${"#\{"}${dataModal.javaFields[i]}${"}"}
          </if>
        </#list>
      </#if>
      </where>
    </sql>