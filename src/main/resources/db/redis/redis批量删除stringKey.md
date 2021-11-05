

 1.redis批量删除Key 命令

```bash
./redis-cli -h localhost  keys "cs-*" | xargs ./redis-cli -h localhost del

redis-cli  -h localhost   -a ynby_redis_2018  keys "cs-*" | xargs redis-cli -h localhost del -a ynby_redis_2018

redis-cli  -h r-wz92ebc7bec07204.redis.rds.aliyuncs.com    -a Ynby2018  keys "ynby:pay:bal:diff:2:1*" | xargs redis-cli -h r-wz92ebc7bec07204.redis.rds.aliyuncs.com -a Ynby2018   del 
```

