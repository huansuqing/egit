package cn.e3.jedis.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cn.e3.jedis.JedisDao;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

@Repository
public class JedisDaoImpl implements JedisDao {


	//注入jedis集群
	@Autowired
	private JedisCluster jedisCluster;
	@Override
	public String set(String key, String value) {
		String str = jedisCluster.set(key, value);
		return str;
	}

	@Override
	public String get(String key) {
		String str = jedisCluster.get(key);
		return str;
	}

	@Override
	public Long hset(String key, String field, String value) {
		Long hset = jedisCluster.hset(key, field, value);
		return hset;
	}

	@Override
	public String hget(String key, String field) {
		String str = jedisCluster.hget(key, field);
		return str;
	}

	@Override
	public Long hdel(String key, String fields) {
		Long hdel = jedisCluster.hdel(key, fields);
		return hdel;
	}

	@Override
	public Long expire(String key, int seconds) {
		Long expire = jedisCluster.expire(key, seconds);
		return expire;
	}

	@Override
	public Long ttl(String key) {
		Long ttl = jedisCluster.ttl(key);
		return ttl;
	}

	@Override
	public Long del(String key) {
		Long del = jedisCluster.del(key);
		return del;
	}

	@Override
	public Long incr(String key) {
		Long incr = jedisCluster.incr(key);
		return incr;
	}

	@Override
	public Boolean exists(String key) {
		Boolean exists = jedisCluster.exists(key);
		return exists;
	}

	@Override
	public Boolean hexists(String key, String field) {
		Boolean hexists = jedisCluster.hexists(key,field);
		return hexists;
	}

	@Override
	public List<String> hvals(String key) {
		List<String> hvals = jedisCluster.hvals(key);
		return hvals;
	}
}
