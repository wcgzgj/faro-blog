这个库的 main branch 被我浪没了。。。所以这个库以后不会再更新，后期更新会放在 faro-blog-rebase库中，因为涉及机密数据，所以我会将库设置为private，等到项目完全完成了，后期可能会选择开放项目代码

# 个人博客

学计算机这么长时间，自己做不出一个个人博客，那也太不像话了，所以，这次的课程设计，我决定做一个个人博客

项目 Github 地址：https://github.com/wcgzgj/faro-blog



## 项目介绍

### 功能介绍

* 实现单点登录功能，使用 MD5 对密码进行加密
* 实现用户管理，并且只有管理员（也就是我）有用户管理权限
* 实现文章撰写、编辑功能，只有登录用户拥有此功能；因为没法像一般的商业技术论坛一样，做内容审核，为了避免我的网站被封，目前我只开放我自己的内容新增、编辑功能
* 实现文章预览，阅读；文章阅读是对所有用户开放的
* 评论功能暂时不实现，因为没有有效的审核机制，我担心网站被封（现在这个环境，干什么事都要小心翼翼的）



### 历史版本

我使用目前比较流行的版本控制工具 Github 存储历史版本，当当前版本出现难以修改的错误，或需要与之前版本进行代码比对的时候，可以通过 github 很方便的实现



**下面每一条记录，都是我所存储的历史版本：**

<img src="https://gitee.com/faro/images/raw/master/img/20210420143006.png" alt="image-20210420143006278" style="zoom:50%;" />



**可以随时进行回退：**

<img src="https://gitee.com/faro/images/raw/master/img/20210420143057.png" alt="image-20210420143057332" style="zoom:50%;" />



所以我认为，没有必要专门将历史版本拷贝出来，放到硬盘里。一是因为这样会增加硬盘负担；二是因为用这种方式进行版本管理，只能存储有限的历史版本，并且如果要进行版本回退，会十分十分的麻烦。



**具体的历史版本，可以在我的 github 仓库查看：**

https://github.com/wcgzgj/faro-blog





### 界面展示

#### 首页

首页左侧是文章列表，显示我做撰写的所有文章；目前里面还存在很多测试数据，所以会出现内容和标题不一致的情况，这需要我后期去维护

右边是我的个人介绍和我的微信公众号链接

![Xnip2021-04-20_14-42-10](https://gitee.com/faro/images/raw/master/img/20210420144220.jpg)



#### header

因为前端是使用 vue编写的，所以组件可以实现复用，每个页面，都会有一个 header 和 footer

![image-20210420144424217](https://gitee.com/faro/images/raw/master/img/20210420144424.png)

头部导航栏包括主页，文章管理，用户登录等功能，



**其中文章管理，是需要登录后，才会显示的：**

![image-20210420144650937](https://gitee.com/faro/images/raw/master/img/20210420144651.png)



#### footer

footer 没什么出彩的，就是我的个人信息

![image-20210420144746857](https://gitee.com/faro/images/raw/master/img/20210420144746.png)



#### 文章预览

点击主页的任意链接，就能显示里面的文章内容，因为有些文章比较长，我还加上了一个回到顶部的按钮

![image-20210420144831247](https://gitee.com/faro/images/raw/master/img/20210420144831.png)



#### 登录

**点击登录，会跳转到登录界面：**

![image-20210420144956412](https://gitee.com/faro/images/raw/master/img/20210420144956.png)

**为了确保网站流量稳定，避免他人蓄意破坏，注册和找回密码功能暂不开放：**

![image-20210420145125320](https://gitee.com/faro/images/raw/master/img/20210420145125.png)



#### 文章管理

不登录的话，文章管理按钮是不会显示的

![image-20210420145326435](https://gitee.com/faro/images/raw/master/img/20210420145326.png)

可以对文章进行删除，编辑，新增操作，也可以根据文章标题，搜索文章

![image-20210420145453773](https://gitee.com/faro/images/raw/master/img/20210420145514.png)

**点击删除，会出现确认提示框：**

![image-20210420145542706](https://gitee.com/faro/images/raw/master/img/20210420145542.png)

**点编辑，会跳转到文章的编辑界面：**

![image-20210420145610944](https://gitee.com/faro/images/raw/master/img/20210420145611.png)

#### 新增/编辑界面

新增、编辑使用的都是同一套前端模板，区别就在于里面有没有内容显示

**编辑：**

![image-20210420145653816](https://gitee.com/faro/images/raw/master/img/20210420145653.png)

**新增：**

默认打开是没有内容的

![image-20210420145809201](https://gitee.com/faro/images/raw/master/img/20210420145809.png)

我们随便写一点内容，右边会实时显示我们添加的内容

![image-20210420145928244](https://gitee.com/faro/images/raw/master/img/20210420145928.png)

点击预览，也会将内容显示出来

![image-20210420150005599](https://gitee.com/faro/images/raw/master/img/20210420150005.png)

点击保存，就能看到我们新增的内容了

![image-20210420150044308](https://gitee.com/faro/images/raw/master/img/20210420150044.png)



#### 用户管理

用户管理，提供编辑，重置密码，删除，搜索，新增这些功能，目前只提供给管理员（也就是我）使用

如果没有登录，在点击这个页面的时候，是会重新跳转到首页的

![image-20210420150108378](https://gitee.com/faro/images/raw/master/img/20210420150108.png)

因为密码使用 MD5 加密，就算是作为管理员的我，也无法知道用户的密码，如果希望修改，就只能进行密码重置

关于什么是 MD5，我后面会介绍

![image-20210420150341372](https://gitee.com/faro/images/raw/master/img/20210420150341.png)



#### 报表统计

因为工期不够，本来是准备在这个界面上显示文章的浏览数目，点赞数目等数据的，现在只能暂时放上我的博客开发进度进行填充

![image-20210420150453006](https://gitee.com/faro/images/raw/master/img/20210420150453.png)

#### 关于我

目前放上的，是我的个人介绍、联系方式等信息

![Xnip2021-04-20_15-10-19](https://gitee.com/faro/images/raw/master/img/20210420151027.jpg)



![Xnip2021-04-20_15-11-02](https://gitee.com/faro/images/raw/master/img/20210420151111.jpg)





## 代码介绍

我这次选择的开始方式，是目前热门的前后端分离开方式，后端使用 SpringBoot 实现，前端使用 Vue 实现，持久层使用 Mybatis 框架，前端 UI 库使用了 ElementUi

因为代码量较大，全部说不现实，所以我就会挑一部分讲一下。



### 后端

#### MVC模式的流程

我们就==挑一个==后端接口，走一下前端数据传输，后端处理，前端再接受展示的流程

要想对这和流程有一定了解，就要对 MVC 设计模式有一定的了解

下面这个图，就大概阐述了 MVC 模式的运行流程

<img src="https://gitee.com/faro/images/raw/master/img/20210420152852.png" alt="image-20210420152852170" style="zoom:50%;" />



我们就以首页的文章搜索为例，展示一个完整的前后端交互流程

##### 前端，异步请求

前端向后端请求的路径，是`/doc/find`，并且查找的条件，是依照 id

```javascript
const queryDoc = (id) => {
  axios.get("/doc/find/"+id).then( (resp) => {
    const data = resp.data;
    if (data.success) {

      doc.value = data.content;

    } else {
      ElMessage("文章加载错误！")
    }
  });
}
```



##### 后端接口接受参数

因为是 restful 风格

```java
@GetMapping("/find/{id}")
    public CommonResp find(@PathVariable("id") Long id) {
        DocQueryResp resp = docService.find(id);
        CommonResp<DocQueryResp> commonResp = new CommonResp<>();
        commonResp.setContent(resp);
        return commonResp;
    }
```



##### 后端接口调用 Service 层代码

如果所有业务实现都写在后端接口里，那会变得十分庞大，所以需要分层

Service 层代码，也调用了 Dao 层，为了控制持久层数据

```java
public DocQueryResp find(Long id) {
        Doc doc = docMapper.selectByPrimaryKey(id);
        DocQueryResp resp = CopyUtil.copy(doc, DocQueryResp.class);
        return resp;
    }
```



##### Service 调用 Dao 层代码

dao 层，就是控制持久层数据的，我使用的是 Mybatis框架

```xml
<select id="selectByPrimaryKey" parameterType="java.lang.Long" resultMap="BaseResultMap">
    select 
    <include refid="Base_Column_List" />
    from doc
    where id = #{id,jdbcType=BIGINT}
  </select>
```



Mybatis 将内容转换成 SQL，能从数据库中查询数据

![image-20210420153901244](https://gitee.com/faro/images/raw/master/img/20210420153901.png)



##### 后端接口将数据返回给前端

![image-20210420154003152](https://gitee.com/faro/images/raw/master/img/20210420154003.png)

**通用返回值 CommonResp 如下：**

里面包含是否错误，错误信息，和包内容三个部分

```java
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommonResp<T> {
    /**
     * 返回是否成功
     */
    private boolean success=true;

    /**
     * 如果不成功，返回错误信息
     */
    private String message;

    /**
     * 返回的数据
     */
    private T content;
}
```



##### 前端接受后端返回数据

![image-20210420154217515](https://gitee.com/faro/images/raw/master/img/20210420154217.png)



上面，就是一个完整的前后端交互流程，我屏蔽了很多技术细节，让上面的流程看起来简洁一点








### 前端

前端，我认为可说的不多。大多数的前端界面实现，我都是使用了现在比较流行的前端 UI 库 ElementUI，并且使用 Vue 框架，大幅提升了我的开发效率。

这里，我就介绍几个 ElementUi 中的组件的使用方法吧。



#### 分页组件

就是下面这个页码

![image-20210420154624768](https://gitee.com/faro/images/raw/master/img/20210420154624.png)

```html
<el-pagination
                  @current-change="handleCurrentChange"
                  :current-page="pageNum"
                  :page-size="pageSize"
                  background
                  layout="prev, pager, next"
                  :total="total">
          </el-pagination>
```

![image-20210420154909523](https://gitee.com/faro/images/raw/master/img/20210420154909.png)

分页组件，会根据总页数，和每页页数，算出一共有多少页，所以，我们后端只要返回当前页的内容列表，和整张表的总页数即可



一般前端进行分页，后端也要有相应的分页操作，不然如果有居心叵测的人，试图使用脚本直接调用后端接口，可能就会一次查出大量数据，轻则导致其他用户响应变慢，==重则导致服务器崩溃==

**下面是后端的分页：**

```java
public PageResp<DocQueryResp> list(DocQueryReq req) {
        DocExample docExample = new DocExample();
        DocExample.Criteria criteria = docExample.createCriteria();

        /**
         * 名字不空，说明是查询
         *
         * 分页之所以可以和查询放在一起，是因为当分页的时候
         * 不会传进 name,所以模糊查询还是将所有内容查询进来
         * 而且 SQL 显示的是 分页和模糊查询写在一起的
         * 所以，也不会出现查询两次 SQL 的情况
         */
        if (!ObjectUtils.isEmpty(req.getName())) {
            criteria.andNameLike("%"+req.getName()+"%");
        }


        PageHelper.startPage(req.getPage(),req.getSize());
        List<Doc> docs = docMapper.selectByExample(docExample);
        List<DocQueryResp> docQueryResps = CopyUtil.copyList(docs, DocQueryResp.class);

        PageInfo info = new PageInfo(docs);
        PageResp<DocQueryResp> pageResp = new PageResp<>();
        pageResp.setTotal(info.getTotal());
        pageResp.setList(docQueryResps);

        return pageResp;
    }
```



使用 PageHelper，根据前端传来的参数，固定查询的页数

![image-20210420155233953](https://gitee.com/faro/images/raw/master/img/20210420155234.png)



#### 表格组件

![image-20210420155343083](https://gitee.com/faro/images/raw/master/img/20210420155343.png)

```html
<el-table
          v-loading="loading"
          :data="docList"
          border
          style="width: 100%; text-align: center">
  <el-table-column
                   label="名称"
                   >
    <template #default="scope">
      <span style="margin-left: 10px">{{ scope.row.name }}</span>
    </template>
  </el-table-column>
  <el-table-column
                   label="介绍"
                   >
    <template #default="scope">
      <span style="margin-left: 10px">{{ scope.row.description }}</span>
    </template>
  </el-table-column>

  <el-table-column
                   label="操作"
                   >
    <template #default="scope">

      <router-link :to="'/admin/docEdit?docId='+scope.row.id">
        <el-button
                   size="mini"
                   @click="">编辑</el-button>
      </router-link>
      &nbsp;&nbsp;
      <el-button
                 size="mini"
                 type="danger"
                 @click="handleDelete(scope.$index, scope.row)">删除</el-button>
    </template>
  </el-table-column>
</el-table>
```

表格组件只要我们自定表头的样式和内容，置于表内容的展示，只要我们通过后端传入一个 数组，el-table 就会根据这个数组，自动进行展示

![image-20210420155639551](https://gitee.com/faro/images/raw/master/img/20210420155639.png)





#### 模态框

当我们进行某些操作的时候，会弹出一个提示框，警告我们是确认还是取消，这个提示框，就是模态框

![image-20210420155719763](https://gitee.com/faro/images/raw/master/img/20210420155719.png)

**点击删除按钮后，会触发 handelDelete 函数：**

![image-20210420160025896](https://gitee.com/faro/images/raw/master/img/20210420160026.png)



**handelDelete 函数中，就会展示 El 组件中的模态框`ElMessageBox.confirm`:**

```javascript
const handleDelete=(index: any, row: any) =>{
  ElMessageBox.confirm('此操作将永久删除该文件, 是否继续?', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    axios.delete("/doc/delete/"+row.id).then((resp)=> {
      const data=resp.data;
      if (data.success) {
        ElMessage.success("删除成功！");
        handelOpen(1,5);
      } else {
        ElMessage.error("删除失败！")
      }
    });
  }).catch(() => {
    ElMessage("已取消删除")
  });
}
```





还有很多组件，如果要全讲完我可能还要再写几千字，所以就点到为止

ElementUI 的官方文档，地址如下：

https://element-plus.gitee.io/#/zh-CN







