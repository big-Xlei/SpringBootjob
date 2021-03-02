package com.example.demo.generator;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {
    private static final String WORKSPACE = "D:\\Data\\policy_backend\\";
    //todo tablename
    private static final String TABLENAME = "sys_policy_info";

    private static final String CODE_MODEL_PATH = "com.example.demo";

    public static void main(String[] args) {

        AutoGenerator mpg = new AutoGenerator();
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        //调整输出文件的路径
        gc.setOutputDir(WORKSPACE + "src\\main\\java");
        gc.setFileOverride(true);
        // 不需要ActiveRecord特性的请改为false
        gc.setActiveRecord(false);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // XML columList
        gc.setBaseColumnList(true);
        // 作者
        gc.setAuthor("wangjie");
        gc.setEntityName("%s");
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
//        gc.setControllerName("%sController");
//        gc.setServiceName("%sService");
//        gc.setServiceImplName("%sServiceImpl");
//        gc.setMapperName("%sMapper");
//        gc.setXmlName("%sMapper");
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("password");
        dsc.setUrl("jdbc:mysql://47.100.42.207:3306/platform?serverTimezone=Asia/Shanghai&amp");
        mpg.setDataSource(dsc);
        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        //添加了lombok注解，@Data直接注解在类上，去掉了getter和setter方法
        strategy.setEntityLombokModel(true);
        //生成@RestController
        strategy.setRestControllerStyle(true);
        // 表名生成策略
        strategy.setNaming(NamingStrategy.underline_to_camel);
        //驼峰形式
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(new String[]{TABLENAME});
        // 此处可以修改为您的表前缀 例如表f_datadictionary，
        // 这边加上f_ 生成后的类目就是DatadictionaryXXX，这边为空就生成类名FDatadictionaryXXX
        strategy.setTablePrefix(new String[]{""});
//        strategy.setSuperServiceClass(null);
//        strategy.setSuperServiceImplClass(null);
//        //自定义继承的Mapper类全称，带包名
//        strategy.setSuperMapperClass("com.maice.hangzhou.policy.server.BaseMapper");
//        strategy.setSuperControllerClass("com.maice.hangzhou.policy.server.BaseController");
        mpg.setStrategy(strategy);
        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(CODE_MODEL_PATH);
//        pc.setController("controller");
//        pc.setService("service");
//        pc.setServiceImpl("service.impl");
//        pc.setMapper("mapper");
        pc.setEntity("beans");
//        pc.setXml("mapper");
        mpg.setPackageInfo(pc);
        // 执行生成
        mpg.execute();
    }
}
