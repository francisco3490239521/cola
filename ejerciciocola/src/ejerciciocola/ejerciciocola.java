class Cola {
private:
    int* queueArray;  
    int capacity;     
    int front;       
    int rear;         
    int count;        

    void resize(int newCapacity) {
        int* newQueueArray = new int[newCapacity];
        int currentSize = size();
        for (int i = 0; i < currentSize; ++i) {
            newQueueArray[i] = queueArray[(front + i) % capacity];
        }
        delete[] queueArray;
        queueArray = newQueueArray;
        capacity = newCapacity;
        front = 0;
        rear = currentSize;
    }

public:
    Cola(int initialCapacity = 10) : capacity(initialCapacity), front(0), rear(0), count(0) {
        if (initialCapacity <= 0) {
            throw std::invalid_argument("Capacity must be positive");
        }
        queueArray = new int[capacity];
    }

    ~Cola() {
        delete[] queueArray;
    }

    void add(int value) {
        if (count == capacity) {
            resize(2 * capacity);  
        }
        queueArray[rear] = value;
        rear = (rear + 1) % capacity;
        ++count;
    }

    int remove() {
        if (isEmpty()) {
            throw std::out_of_range("Cola vacía");
        }
        int value = queueArray[front];
        front = (front + 1) % capacity;
        --count;
        return value;
    }

    bool isEmpty() const {
        return count == 0;
    }

    int size() const {
        return count;
    }
};

int main() {
    Cola cola;

    cola.add(10);
    cola.add(20);
    cola.add(30);

    std::cout << "Elemento eliminado: " << cola.remove() << std::endl;  
    std::cout << "Elemento eliminado: " << cola.remove() << std::endl;  

    return 0;
}
