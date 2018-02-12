

为防止项目出现循环依赖<br>
moduel模块下dependencyManagement部署的包分给各个moduel应当只引用一次<br>
故对于moduel子模块间的相互引用应当遵循如下规则:<br>
1.web只引用shiro子模块、moduel部署包中的spring等web模块(除spring-beans外)<br>
2.shiro只引用service子模块、moduel部署包中的apache-shiro和spring-beans模块<br>
3.service只引用dao子模块、moduel部署包中的spring-beans模块<br>
4.dao只引用entity子模块<br>
