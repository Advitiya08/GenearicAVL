# Generic AVL Tree in Java (with Duplicate Support)

A high-performance, generic, self-balancing AVL Tree implementation in Java that:

- Uses `Comparable<T>` to structure the tree
- Supports duplicates via `List<T>` per node
- Allows optional sorting of duplicates via a custom comparator
- Offers clean O(log n) insert/delete while preserving insertion order if needed

## 🚀 Features

- ✅ Generic: `T extends Comparable<T>`
- ✅ Stores duplicate elements efficiently
- ✅ Optional `duplicateSort` comparator to sort duplicates within a node
- ✅ Preserves insertion order of duplicates if no sort provided
- ✅ Clean API: `insert`, `delete`, `inorder`
- ✅ Self-balancing with LL, RR, LR, RL rotations

## 📦 How to Use

### 🧪 Example Class

```java
public class Person implements Comparable<Person> {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age); // AVL structure by age
    }

    @Override
    public String toString() {
        return name + "(" + age + ")";
    }
}
```

### 🧰 Insert and Traverse

```java
public class Main {
    public static void main(String[] args) {
        Comparator<Person> nameSort = Comparator.comparing(p -> p.name);
        AVLTree<Person> tree = new AVLTree<>(nameSort);

        tree.insert(new Person("Alice", 30));
        tree.insert(new Person("Bob", 30));   // Same age, sorted by name
        tree.insert(new Person("Charlie", 25));

        System.out.print("Inorder: ");
        tree.inorder(); // Output: Charlie(25) Alice(30) Bob(30)
    }
}
```

## 🔧 API

| Method              | Description                                               |
|---------------------|-----------------------------------------------------------|
| `insert(T value)`   | Inserts value in O(log n), allowing duplicates            |
| `delete(T value)`   | Deletes one matching value (respects duplicate sort if any) |
| `inorder()`         | Prints in-order traversal, duplicates preserved           |
| `AVLTree(Comparator<T> duplicateSort)` | Optional comparator to sort duplicates |

## 📁 File Structure Suggestion

```plaintext
src/
├── AVLTree.java
├── AVLNode.java (optional: inline it in AVLTree)
├── Main.java
└── README.md
```

## 💡 Design Highlights

- All duplicates of a key are stored in a `List<T>` at that node
- Comparisons use `T.compareTo()` for tree structure
- Sorting within duplicates uses an optional `Comparator<T>`

## 📜 License

MIT License (or your choice)
