package review.recycler;

import io.netty.util.Recycler;

/**
 * 参考{@link io.netty.buffer.PooledUnsafeDirectByteBuf}
 * @author: xiaoxiaoxiang
 * @date: 2021/1/8 10:45
 */
public class RecyclerDemo {

    private static final Recycler<User> RECYCLER = new Recycler<User>() {
        @Override
        protected User newObject(Handle<User> handle) {
            return new User(handle);
        }
    };

    private static class User {
        private final Recycler.Handle<User> handle;

        public User(Recycler.Handle<User> handle) {
            this.handle =  handle;
        }

        public void recycle() {
            handle.recycle(this);
        }
    }

    public static void main(String[] args) {
        // 对象池为空则创建对象
        User user = RECYCLER.get();
        // 回收对象
        user.recycle();
        // 对象池不为空则复用对象
        User user1 = RECYCLER.get();
        // 输出true,可见是同一个对象
        System.out.println(user1 == user);
        // 不回收对象,继续去对象池获取(此时对象池为空,就会去创建新对象)
        User user2 = RECYCLER.get();
        // 输出false,可见是同一个对象
        System.out.println(user1 == user2);
    }
}
