package cn.e3.jedis;

import java.util.List;

public interface JedisDao {

	//String类型
	public String set(String key,String value);
	//String get
	public String get(String key);
	//string del
	public Long del(String key);
	//hash类型
	public Long hset(String key, String field, String value);
	//hash get
	public String hget(String key, String field);
	//hash delete
	public Long hdel(String key,String fields);
	//设置数据过期
	public Long expire(String key,int seconds);
	//测试过期时间过程
	public Long ttl(String key);
	//获取一个redis的自增张数据
	public Long incr(String key);
	//判断指定的key值是否存在
	public Boolean exists(String key);
	//判断指定的key值是否存在(hash类型)
	public Boolean hexists(String key,String field);
	//更具key查找hash值
	public List<String> hvals( String key); 
}
