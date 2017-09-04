package com.david.mavenessh.web.action.sysadmin;

import java.util.List;

import com.david.mavenessh.domain.Dept;
import com.david.mavenessh.service.DeptService;
import com.david.mavenessh.utils.Page;
import com.david.mavenessh.web.action.base.BaseAction;
import com.opensymphony.xwork2.ModelDriven;

/**
 * 部门管理的action
 * @Author ：程序员小冰
 * @新浪微博 ：http://weibo.com/mcxiaobing
 * @GitHub: https://github.com/QQ986945193
 */
public class DeptAction extends BaseAction implements ModelDriven<Dept> {

	private Dept model = new Dept();
	@Override
	public Dept getModel() {
		return model;
	}
	//分页查询
	private Page page = new Page<>();
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	//注入DpetService
	private DeptService deptService;
	public void setDeptService(DeptService deptService) {
		this.deptService = deptService;
	}
	
	/**
	 * 分页查询  默认每页显示十条数据
	 */
	public String list() throws Exception{
		deptService.findPage("from Dept", page, Dept.class, null);
		//设置分页的url
		page.setUrl("deptAction_list");
		//设置每页显示数量为2
		//page.setPageSize(2);
		//将page对象压入栈顶
		super.push(page);
		return "list";
	}
	
	/**
	 * 查看详情
	 * 由于使用了模型驱动，所以这里直接获得
	 */
	public String toview()throws Exception{
		//调用业务方法，根据id得到对象..
		Dept dept = deptService.get(Dept.class, model.getId());
		//放入栈顶
		super.push(dept);
		//跳转页面
		return "toview";
	}
	/**
	 * 进入新增页面
	 */
	public String tocreate(){
		//调用业务方法.得到部门列表
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		//将查询的结果放入值栈中，它放在context区域中
		super.put("deptList", deptList);
		//页面
		return "tocreate";
	}
	
	
	/**
	 * 保存 插入
	 *  <s:select name="parent.id"
	 *     <input type="text" name="deptName" value=""/>
	 * model对象能接收
	 *      parent 
	 *           id
	 *      deptName
	 */
	public String insert() throws Exception{
		//调用业务的方法，实现保存
		deptService.saveOrUpdate(model);
		//跳转页面
		return "alist";
	}
	
	/**
	 * 进入修改的页面
	 */
	public String toupdate()throws Exception{
		//根据id，得到对象
		Dept dept = deptService.get(Dept.class,model.getId());
		//将对象放入到值栈中
		super.push(dept);
		//查询父部门 父部门是多级
		List<Dept> deptList = deptService.find("from Dept where state = 1", Dept.class, null);
		//将查询的结果放入到值栈中。
		super.put("deptList", deptList);
		return "toupdate";
		
	}
	

	/**
	 * 修改
	 */
	public String update()throws Exception{
		//根据id，得到对象
		Dept dept = deptService.get(Dept.class,model.getId());
		//Dept [id=297e5bae5e2c1f57015e2c2238890002, deptName=工人, parent=Dept [id=aeb1c7d3-9a54-4f73-b0ec-0325b83aef45, deptName=null, parent=null, state=null], state=null]
		System.out.println(model);
		//设置修改的属性
		dept.setParent(model.getParent());
		dept.setDeptName(model.getDeptName());
		
		deptService.saveOrUpdate(dept);
		return "alist";
		
	}
	/**
	 * 删除
	 * 	 * <input type="checkbox" name="id" value="100"/>
	 * <input type="checkbox" name="id" value="3d00290a-1af0-4c28-853e-29fbf96a2722"/>
	 * .....
	 * model
	 *    id:String类型
	 *       具有同名框的一组值如何封装数据？
	 *       如何服务端是String类型：
	 *                       100, 3d00290a-1af0-4c28-853e-29fbf96a2722, 3d00290a-1af0-4c28-853e-29fbf96a2722
	 *                       
	 *    id:Integer,Float,Double.Date类型                  id=100               id=200        id=300  
	 *    id=300
	 *    Integer []id;  {100,200,300}
	 *        
	 *           
	 */
	
	public String delete()throws Exception{
		String ids[] = model.getId().split(", ");
	/*	System.out.println(ids.length);
		for (int i= 0;  i< ids.length; i++) {
			System.out.println(ids[i]);
		}*/
		//调用业务方法，实现批量删除
		deptService.delete(Dept.class, ids);
		return "alist";//重定向列表
	}
}
