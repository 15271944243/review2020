--令牌桶token的key
local tokens_key = KEYS[1]
--令牌桶时间的key
local timestamp_key = KEYS[2]
redis.log(redis.LOG_WARNING, "tokens_key " .. tokens_key)
--每秒补充多少个令牌,即每秒允许多少个请求
local rate = tonumber(ARGV[1])
--令牌痛最大容量
local capacity = tonumber(ARGV[2])
--当前系统时间
local now = tonumber(ARGV[3])
--每次请求获取的令牌数
local requested = tonumber(ARGV[4])
--要多少秒可以填充满令牌痛
local fill_time = capacity/rate
--过期时间
local ttl = math.floor(fill_time*2)

--redis.log(redis.LOG_WARNING, "rate " .. ARGV[1])
--redis.log(redis.LOG_WARNING, "capacity " .. ARGV[2])
--redis.log(redis.LOG_WARNING, "now " .. ARGV[3])
--redis.log(redis.LOG_WARNING, "requested " .. ARGV[4])
--redis.log(redis.LOG_WARNING, "filltime " .. fill_time)
--redis.log(redis.LOG_WARNING, "ttl " .. ttl)
--当前token数量
local last_tokens = tonumber(redis.call("get", tokens_key))
--如果为null,则说明未初始化,初始化为最大容量
if last_tokens == nil then
  last_tokens = capacity
end
--redis.log(redis.LOG_WARNING, "last_tokens " .. last_tokens)
--最近一次访问的时间
local last_refreshed = tonumber(redis.call("get", timestamp_key))
--如果为null,则说明未初始化,出事化为0
if last_refreshed == nil then
  last_refreshed = 0
end
--redis.log(redis.LOG_WARNING, "last_refreshed " .. last_refreshed)
--当前时间与最近一次访问时间的时间差
local delta = math.max(0, now-last_refreshed)
--根据时间差计算最新的令牌数,不能超过capacity
local filled_tokens = math.min(capacity, last_tokens+(delta*rate))
--当前令牌数是否足够,如果足够,则允许请求访问
local allowed = filled_tokens >= requested
local new_tokens = filled_tokens
local allowed_num = 0
if allowed then
  new_tokens = filled_tokens - requested
  allowed_num = 1
end

--redis.log(redis.LOG_WARNING, "delta " .. delta)
--redis.log(redis.LOG_WARNING, "filled_tokens " .. filled_tokens)
--redis.log(redis.LOG_WARNING, "allowed_num " .. allowed_num)
--redis.log(redis.LOG_WARNING, "new_tokens " .. new_tokens)

if ttl > 0 then
  --将本次访问的剩余令牌数和时间戳写会redis
  redis.call("setex", tokens_key, ttl, new_tokens)
  redis.call("setex", timestamp_key, ttl, now)
end

-- return { allowed_num, new_tokens, capacity, filled_tokens, requested, new_tokens }
return { allowed_num, new_tokens }
