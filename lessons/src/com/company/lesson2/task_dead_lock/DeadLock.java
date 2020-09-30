package com.company.lesson2.task_dead_lock;

public class DeadLock {

    static class Friend {
        private final String name;

        public Friend(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
            System.out.format("%s: стреляю в ответ!\n", this.name);
            bower.bowBack(this);
        }

        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s подстрелил меня!\n", this.name, bower.getName());
        }
    }

    /**
     * Точка входа в программу
     *
     * @param args аргументы командной строки
     */
    public static void main(String[] args) throws InterruptedException {
        Friend alphonse = new Friend("Alphonse");
        Friend gaston = new Friend("Gaston");

        Thread thread1 = new Thread(() -> alphonse.bow(gaston));
        thread1.start();
        thread1.join();
        new Thread(() -> gaston.bow(alphonse)).start();
    }
}

