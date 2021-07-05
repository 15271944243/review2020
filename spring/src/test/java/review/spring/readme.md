1. @RunWith(MockitoJUnitRunner.class) 与  @RunWith(SpringRunner.class)
```
单元测试中使用@Mock, @Spy, @InjectMocks等注解时,需要进行初始化后才能使用
@RunWith(MockitoJUnitRunner.class) 和 MockitoAnnotations.initMocks(this) 对UnitTest提供mock初始化工作
若在单元测试类中使用了@RunWith(SpringRunner.class) 就不能再使用@RunWith(SpringJUnit4ClassRunner.class),此时就可以使用 
MockitoAnnotations.initMocks(this)


@RunWith(SpringRunner.class) 是当需要加载spring上下文(创建spring bean,执行依赖注入等)时使用,用于集成测试
```


https://www.cnblogs.com/myitnews/p/12330297.html