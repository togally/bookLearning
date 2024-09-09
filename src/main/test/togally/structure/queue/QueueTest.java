package togally.structure.queue;

import cn.hutool.core.util.NumberUtil;
import com.togally.structure.queue.CircleQueue;
import com.togally.structure.queue.Queue;
import org.junit.Assert;
import org.junit.Test;

public class QueueTest {
    @Test
    public void test(){
        testQueue(new CircleQueue<>(3));
    }

    public void testQueue(Queue<Integer> queue){
        Assert.assertTrue(queue.queueEmpty());

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        Assert.assertEquals(3,queue.queueLength());
        Assert.assertFalse(queue.queueEmpty());

        // 获取头部
        Assert.assertTrue(NumberUtil.equals(1,queue.getHead()));
        Assert.assertEquals(3,queue.queueLength());

        // 删除队头并用返回元素
        Assert.assertTrue(NumberUtil.equals(1,queue.deQueue()));
        Assert.assertEquals(2,queue.queueLength());

        // 删除队头并用返回元素
        Assert.assertTrue(NumberUtil.equals(2,queue.deQueue()));
        Assert.assertEquals(1,queue.queueLength());

        // 删除队头并用返回元素
        queue.clearQueue();;
        Assert.assertEquals(0,queue.queueLength());

        queue.destroyQueue();
    }
}
