import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.NoSuchElementException;

public class IntQueueTest {
    private IntQueue mQueue;
    @Before
    public void setUp() {
        mQueue = new LinkedIntQueue();
    }
    @Test
    public void testNewQueueIsEmpty() {
        assertTrue("Шинэ queue хоосон байх ёстой", mQueue.isEmpty());
    }

    @Test
    public void testNewQueueSizeIsZero() {
        assertEquals("Шинэ queue-ийн хэмжээ 0 байх ёстой", 0, mQueue.size());
    }

    @Test
    public void testIsNotEmptyAfterEnqueue() {
        mQueue.enqueue(5); 
        assertFalse("Элемент нэмсний дараа isEmpty() false байх ёстой", mQueue.isEmpty());
    }
    @Test
    public void testSizeAfterOneEnqueue() {
        mQueue.enqueue(42);
        assertEquals("Нэг элемент нэмсний дараа size() 1 байх ёстой", 1, mQueue.size());
    }

    @Test
    public void testDequeueReturnsEnqueuedValue() {
        mQueue.enqueue(99);
        int result = mQueue.dequeue();
        assertEquals("dequeue() нэмсэн утгыг буцааж өгнө", 99, result);
    }

    @Test
    public void testFIFOOrder() {
        mQueue.enqueue(10);
        mQueue.enqueue(20);
        mQueue.enqueue(30);

        assertEquals("Эхний dequeue нь 10 байх ёстой", 10, mQueue.dequeue());
        assertEquals("Хоёрдахь dequeue нь 20 байх ёстой", 20, mQueue.dequeue());
        assertEquals("Гуравдахь dequeue нь 30 байх ёстой", 30, mQueue.dequeue());
    }

    @Test
    public void testSizeDecreasesAfterDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        assertEquals("2 элемент нэмэхэд size=2", 2, mQueue.size());

        mQueue.dequeue();
        assertEquals("1 dequeue хийхэд size=1", 1, mQueue.size());

        mQueue.dequeue();
        assertEquals("2 dequeue хийхэд size=0", 0, mQueue.size());
    }

    @Test
    public void testIsEmptyAfterAllDequeued() {
        mQueue.enqueue(7);
        mQueue.dequeue();
        assertTrue("Бүх элемент гарсны дараа isEmpty() true байх ёстой", mQueue.isEmpty());
    }

    @Test
    public void testPeekDoesNotRemoveElement() {
        mQueue.enqueue(55);
        int peeked = mQueue.peek();

        assertEquals("peek() зөв утга буцаана", 55, peeked);
        assertEquals("peek() хийсний дараа хэмжээ хэвээр байна", 1, mQueue.size());
        assertFalse("peek() хийсний дараа isEmpty() false байна", mQueue.isEmpty());
    }

    @Test
    public void testPeekAndDequeueReturnSameValue() {
        mQueue.enqueue(33);
        int peeked = mQueue.peek();
        int dequeued = mQueue.dequeue();
        assertEquals("peek() ба dequeue() ижил утга буцаана", peeked, dequeued);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDequeueOnEmptyQueueThrowsException() {
        mQueue.dequeue(); 
    }
    
    @Test(expected = NoSuchElementException.class)
    public void testPeekOnEmptyQueueThrowsException() {
        mQueue.peek();
    }

    @Test
    public void testMixedEnqueueDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.dequeue(); 

        mQueue.enqueue(3);

        assertEquals("Холилдсон үйлдлийн дараа эхний элемент 2 байх ёстой", 2, mQueue.dequeue());
        assertEquals("Дараагийн элемент 3 байх ёстой", 3, mQueue.dequeue());
        assertTrue("Бүгдийг авсны дараа хоосон", mQueue.isEmpty());
    }
    
    @Test
    public void testEnqueueNegativeNumber() {
        mQueue.enqueue(-10);
        assertEquals("Сөрөг тоо зөв нэмэгдэнэ", -10, mQueue.dequeue());
    }

    @Test
    public void testEnqueueZero() {
        mQueue.enqueue(0);
        assertEquals("Тэг зөв нэмэгдэнэ", 0, mQueue.dequeue());
    }

    @Test
    public void testMultipleElements() {
        for (int i = 1; i <= 5; i++) {
            mQueue.enqueue(i * 10);
        }
        assertEquals("5 элемент нэмэхэд size=5", 5, mQueue.size());

        for (int i = 1; i <= 5; i++) {
            assertEquals(i * 10, mQueue.dequeue());
        }
    }

    @Test
    public void testCircularBehavior() {
        mQueue.enqueue(100);
        mQueue.enqueue(200);
        mQueue.dequeue();

        mQueue.enqueue(300);

        assertEquals("200 гарах ёстой", 200, mQueue.dequeue());
        assertEquals("300 гарах ёстой", 300, mQueue.dequeue());
        assertTrue("Хоосон байх ёстой", mQueue.isEmpty());
    }

    @Test
    public void testSizeAfterDequeue() {
        mQueue.enqueue(1);
        mQueue.enqueue(2);
        mQueue.dequeue();
        assertEquals("dequeue хийсний дараа size() зөв байх ёстой", 1, mQueue.size());
    }
}
