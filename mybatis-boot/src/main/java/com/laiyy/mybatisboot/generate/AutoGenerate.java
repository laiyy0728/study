package com.laiyy.mybatisboot.generate;

import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.Collections;

/**
 * @author laiyy
 * @date 2018/8/23 17:26
 * @description
 *
 * MyBatis Plus 代码生成器
 */
public class AutoGenerate {

    private static final String URL = "jdbc:mysql://10.10.10.249:3306/t1?useUnicode=true&characterEncoding=utf-8";
    private static final String USERNAME = "t1";
    private static final String PASSWORD = "817j0JK_x8";
    private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        String tableNames = "zw_attachment,zw_authority,zw_authority_template,zw_authority_template_authority,zw_chain,zw_chain_log,zw_channel,zw_channel_government_level,zw_channel_library,zw_channel_news_sort,zw_channel_site_dep,zw_channel_user,zw_dataexchange_modulebo,zw_department,zw_doc_type,zw_filter_word,zw_group,zw_hot_word,zw_label,zw_label_news_channel,zw_label_type,zw_material,zw_material_type,zw_modular,zw_modular_channel,zw_modular_log,zw_news,zw_news_content,zw_news_content_log,zw_news_log,zw_news_number,zw_news_source,zw_old_news_log,zw_report_news,zw_report_news_content,zw_role,zw_role_authority,zw_role_authority_template,zw_schedule_job,zw_sensitive_word,zw_site,zw_site_group,zw_site_properties,zw_site_up_down,zw_static_resource,zw_static_resource_group,zw_subject,zw_subject_content,zw_subject_log,zw_subject_template,zw_subject_template_content,zw_subject_type,zw_template,zw_template_content,zw_template_history,zw_template_history_content,zw_theme,zw_user_role,zw_user_site,zw_word_log";
        generateTables(false, "com.laiyy.mybatis", tableNames.split(","));
    }

    private static void generateTables(boolean serviceNameStartWithI, String packageName, String... tables) {
        // 1、数据库连接配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig()
                // 设置数据库连接类型为 MYSQL
                .setDbType(DbType.MYSQL)
                // 设置链接属性
                .setUrl(URL)
                .setDriverName(DRIVER_CLASS)
                .setPassword(PASSWORD)
                .setUsername(USERNAME);

        // 2、生成策略配置
        StrategyConfig strategyConfig = new StrategyConfig()
                // 设置哪个字段表示版本
                .setVersionFieldName("")
                // 设置哪些字段需要自动填充，并设置填充规则
                .setTableFillList(Collections.singletonList(new TableFill("status", FieldFill.INSERT)))
                // 设置业务实现类的父类，如果不设置，则默认为  ServiceImpl<TMapper, T>
                .setSuperServiceImplClass("")
                // 设置业务接口的父类，如果不设置，则默认为 IService<T>
                .setSuperServiceClass("")
                // 设置 Mapper 的父类，如果不设置，则默认为 BaseMapper<T>
                .setSuperMapperClass("")
                // 设置父类实体的字段名
                .setSuperEntityColumns("")
                // 设置实体的父类，如果不设置，默认为  Model<T>
                .setSuperEntityClass("")
                // 设置 Controller 的父类
                .setSuperControllerClass("")
                // 设置是否是 RestController
                .setRestControllerStyle(false)
                // 设置表中哪些字段是表示逻辑删除的
                .setLogicDeleteFieldName("")
                // 设置哪些表不生成
                .setExclude()
                // 是否生成字段常量，如果设置为 true，则会在生成的实体类中自动生成  public static final String ID = "id"; public static final String CREATE_DATE = "create_date"; 等常量
                .setEntityColumnConstant(true)
                // 是否为构建者模型，如果为 true，则 set 方法将返回对象本身，而不是 void
                .setEntityBuilderModel(false)
                // 为 true 将 Controller 的驼峰式命名转换为连接符，如：  getNewsById 转换为： get-news-by-id
                .setControllerMappingHyphenStyle(true)
                // 是否大写命名
                .setCapitalMode(true)
                // 是否为 Lombok 类型（@Getter @Setter @ToString 等注解）
                .setEntityLombokModel(false)
                // 表名、字段名是否启动下划线
                .setDbColumnUnderline(false)
                // 数据库字段转换为实体属性名时的策略： nochange 不做任何改变，underline_to_camel 下划线转为驼峰命名
                .setNaming(NamingStrategy.underline_to_camel)
                // 数据库表字段映射到实体属性的命名策略，如果未指定的话按照 Naming 执行，如果指定的话， Naming 失效，按照  ColumnNaming 执行
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 设置表前缀
                .setTablePrefix("zw_")
                // 设置实体是否去除表前缀
                .setEntityBooleanColumnRemoveIsPrefix(true)
                // 设置生成哪些表
                .setInclude(tables);
        // 设置属性的前缀
        strategyConfig.setFieldPrefix(new String[]{});

        // 3、全局配置
        GlobalConfig globalConfig = new GlobalConfig()
                // 设置 xml 文件名称，默认为 %sMapper.xml
                .setXmlName("")
                // 设置 Mapper 文件名称，默认为  %sMapper ：实体类Mapper
                .setMapperName("")
                // 是否按照 Kotlin 格式输出
                .setKotlin(true)
                // 设置 id 生成规则，AUTO 自增，INPUT 手动输入，ID_WORKER 分布式全局唯一， UUID uuid， NONE 没有设置， ID_WORKER_STR 分布式全局唯一字符串id
                // 其中 ID_WORKER、UUID 只有当对象 ID 为空时，才会自动填充
                .setIdType(IdType.AUTO)
                // 设置 Controller 名称
                .setControllerName("")
                // 是否开启二级缓存
                .setEnableCache(false)
                // 是否在 Mapper.xml 文件中生成 ResultMap
                .setBaseResultMap(true)
                // 是否在 Mapper.xml 中加载 SQL 字段对应
                .setBaseColumnList(true)
                // 领域模型
                .setActiveRecord(true)
                // 作者
                .setAuthor("laiyy")
                // 输出到哪个文件夹下
                .setOutputDir("d:\\mybatis")
                // 是否覆盖创建
                .setFileOverride(true);

        if (!serviceNameStartWithI) {
            // 如果 Service 接口命名不以 I 开头
            globalConfig.setServiceName("%sService");
        }

        // 4、设置自动构建
        AutoGenerator autoGenerator = new AutoGenerator()
                // 设置全局配置
                .setGlobalConfig(globalConfig)
                // 设置数据库配置
                .setDataSource(dataSourceConfig)
                // 设置映射策略
                .setStrategy(strategyConfig)
                // 设置注入配置（在有对外需求的时候需要）
//                .setCfg()
//                设置模版配置（完全默认即可）
//                .setTemplate()
                // 设置模版引擎，在设置模版配置时必须指定
//                .setTemplateEngine()
                // 设置包
                .setPackageInfo(new PackageConfig()
                        // 设置主包名
                        .setParent(packageName)
                        // 设置 Controller 包名（默认 controller）
                        .setController("controller")
                        // 设置数据库实体类包名 （默认 entity）
                        .setEntity("entity")
                        // 设置主包模块名,如果设置了，则会生成到  parent.moduleName 下
                        .setModuleName("laiyy")
                        // Mapper.xml 文件包（默认 mapper.xml）
                        .setXml("")
                        // Mapper 文件包（默认 mapper）
                        .setMapper("")
                        // service 文件包（默认 service）
                        .setService("")
                        // serviceImpl 文件包（默认 service.impl）
                        .setServiceImpl("")
                );

        // 5、开始构建生成
        autoGenerator.execute();
    }

}
