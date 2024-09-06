## 问题背景
新搭了一个脚手架,发现简单的分页接口timing有3000多ms，于是开始了问题定位之路

## 定位过程
- 首先需要排除sql问题，脚手架使用了 dynamic-datasource-spring-boot-starter 直接开启性能分析配置即可。查看到sql耗时都在50ms左右，完全没有问题。
- 考虑到可能是业务代码加了日志之后发现都没问题
- 于是就从DispatcherServlet一步步用二分法打断点调试，最终定位到是jackson序列化过慢导致的

## 解决方法
换用fastjson
```java
  @Bean
    public HttpMessageConverters fastJson() {
        //1.需要定义一个convert转换消息的对象;
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //2:添加fastJson的配置信息;
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,SerializerFeature.WriteDateUseDateFormat);
        //3处理中文乱码问题
        List<MediaType> fastMediaTypes = new ArrayList<>();
        fastMediaTypes.add(MediaType.APPLICATION_JSON);
        //4.在convert中添加配置信息.
        fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }
```
