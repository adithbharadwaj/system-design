from threading import Thread, Lock

class Subscriber:
    def __init__(self):
        print('created subscriber')
        self.lock = Lock()

    def send(self, value):
        print(value)

class PubSub:
    def __init__(self):
        self.subscribers = []
        self.queue = []
        self.lock = Lock()
        Thread(target=self.worker_thread).start()

    def publish(self, value):
        self.queue.append(value)

    def add_subscriber(self, subscriber):
        self.subscribers.append(subscriber)

    def worker_thread(self):
        while True:
            self.lock.acquire()
            try:
                if len(self.queue) > 0:
                    value = self.queue.pop(0)

                    for subscriber in self.subscribers:
                        subscriber.send(value)
            finally:
                self.lock.release()

if __name__ == '__main__':
    s1, s2, s3 = Subscriber(), Subscriber(), Subscriber()
    message_bus = PubSub()

    message_bus.add_subscriber(s1)
    message_bus.add_subscriber(s2)
    message_bus.add_subscriber(s3)

    message_bus.publish('hello world 1')
    message_bus.publish('hello world 2')
    message_bus.publish('hello world 3')
    message_bus.publish('hello world 4')
    message_bus.publish('hello world 5')
    message_bus.publish('hello world 6')


