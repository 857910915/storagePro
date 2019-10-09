package face;

import java.util.List;
import bean.Person;

/**
 * 
 * @author 陈世杰
 * 用户模块功能接口
 */

public interface PersonFace {

	//获取用户信息
	public List<Person> getPersons(int page);
	
	//添加用户信息
	public int addPerson(Person person);
	
	//删除用户信息
	public boolean deletePerson(int perid);
	
	//修改用户信息
	public boolean updatePerson(Person person);
}
