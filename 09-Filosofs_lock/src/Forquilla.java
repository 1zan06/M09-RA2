
import java.util.concurrent.locks.ReentrantLock;

public class Forquilla {
    private int num;
    private ReentrantLock bloqueig;


    public Forquilla() {

    }

    public Forquilla(int num) {
        this.num = num;
        this.bloqueig = new ReentrantLock(true);
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void agafar() {
        bloqueig.lock();
    }

    public void deixar() {
        if (bloqueig.isHeldByCurrentThread()) {
            bloqueig.unlock();
        }
    }

    public ReentrantLock getBloqueig() {
        return bloqueig;
    }

    public void setBloqueig(ReentrantLock bloqueig) {
        this.bloqueig = bloqueig;
    }
}
