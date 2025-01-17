package togally.structure.stack;

import com.togally.structure.stack.*;
import org.junit.Assert;
import org.junit.Test;


public class StackTest {

    @Test
    public void arrayStackTest(){
        test(new ArrayStack<>());
    }

    @Test
    public void linkedStackTest(){
        test(new LinkedStack<>());
    }

    @Test
    public void shardedStackTest(){
        // 初始化
        IShardedStack<Integer> stack = new SharedStack<>();
        stack.init();

        // 栈为空
        Assert.assertTrue(stack.stackEmpty(1));
        Assert.assertTrue(stack.stackEmpty(2));

        // 压栈
        stack.push(1,1);
        stack.push(1,2);
        Assert.assertFalse(stack.stackEmpty(1));
        Assert.assertFalse(stack.stackEmpty(2));
        Assert.assertEquals(1, stack.stackLength(1));
        Assert.assertEquals(1, stack.stackLength(2));
        Assert.assertEquals(1, stack.getTop(1).intValue());
        Assert.assertEquals(1, stack.getTop(2).intValue());


        // 压栈
        stack.push(2,1);
        stack.push(2,2);
        Assert.assertEquals(2, stack.stackLength(1));
        Assert.assertEquals(2, stack.stackLength(2));
        Assert.assertEquals(2, stack.getTop(1).intValue());
        Assert.assertEquals(2, stack.getTop(2).intValue());

        // 获取top后不减少栈长度
        Assert.assertEquals(2, stack.getTop(1).intValue());
        Assert.assertEquals(2, stack.stackLength(1));
        Assert.assertEquals(2, stack.getTop(2).intValue());
        Assert.assertEquals(2, stack.stackLength(2));

        // 弹栈后长度减少
        Assert.assertEquals(2, stack.pop(1).intValue());
        Assert.assertEquals(1, stack.stackLength(1));
        Assert.assertEquals(2, stack.pop(2).intValue());
        Assert.assertEquals(1, stack.stackLength(2));

        // 清空栈数据清空
        stack.clearStack(1);
        Assert.assertEquals(0, stack.stackLength(1));
        stack.clearStack(2);
        Assert.assertEquals(0, stack.stackLength(2));

        // 栈销毁
        stack.destroyStack(1);
        stack.destroyStack(2);
    }


    /**
     * 通用测试方法
     *
     * @param stack 栈
     */
    public void test(IStack<Integer> stack) {
        // 初始化
        stack.init();
        Assert.assertNotNull(stack.getList());

        // 栈为空
        Assert.assertTrue(stack.stackEmpty());
        // 压栈
        stack.push(1);
        Assert.assertFalse(stack.stackEmpty());
        Assert.assertEquals(1, stack.stackLength());
        Assert.assertEquals(1, stack.getTop().intValue());

        // 压栈
        stack.push(2);
        Assert.assertEquals(2, stack.stackLength());
        Assert.assertEquals(2, stack.getTop().intValue());

        // 获取top后不减少栈长度
        Assert.assertEquals(2, stack.getTop().intValue());
        Assert.assertEquals(2, stack.stackLength());

        // 弹栈后长度减少
        Assert.assertEquals(2, stack.pop().intValue());
        Assert.assertEquals(1, stack.stackLength());

        // 清空栈数据清空
        stack.clearStack();
        Assert.assertEquals(0, stack.stackLength());

        // 栈销毁
        stack.destroyStack();
        Assert.assertNull(stack.getList());
    }
}
