package microbots.common.core.registry;

import microbots.api.IRobit;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class RobitRegistry<T extends IRobit> {
  private final Map<String, T> robits = new ConcurrentHashMap<>();
  private final Lock lock = new ReentrantLock();

  public Collection<T> all() {
    try {
      this.lock.lock();
      return this.robits.values();
    } finally {
      this.lock.unlock();
    }
  }

  public void reset() {
    try {
      this.lock.lock();
      this.robits.clear();
    } finally {
      this.lock.unlock();
    }
  }

  public boolean contains(String id) {
    try {
      this.lock.lock();
      return id != null && this.robits.containsKey(id);
    } finally {
      this.lock.unlock();
    }
  }

  public T get(String id) {
    try {
      this.lock.lock();
      return this.robits.get(id);
    } finally {
      this.lock.unlock();
    }
  }

  public void remove(String id) {
    try {
      this.lock.lock();
      if (this.robits.containsKey(id)) {
        this.robits.remove(id);
      }
    } finally {
      this.lock.unlock();
    }
  }

  public void register(T robit) {
    try {
      this.lock.lock();
      this.robits.put(robit.id(), robit);
    } finally {
      this.lock.unlock();
    }
  }
}