# Concurrency basics

Little playground around [Java concurrency tutorial](https://docs.oracle.com/javase/tutorial/essential/concurrency/index.html).

## [CompletionStage](https://docs.oracle.com/en/java/javase/21/docs/api/java.base/java/util/concurrent/CompletionStage.html)
- Represents a stage in a possibly asynchronous operation

### Method classification
#### By what they are doing with the result of previous stage/-s
- `*apply*()`
  - takes `Function<? super T>`
- `*accept*()`
  - takes `Consumer<? super T>`
- `*run*()`
  - takes `Runnable`

#### By dependency on previous stage/-s
- `*then*()`
  - dependency on the previous stage only
- `*both*()`
  - dependency on the previous two stages, both needed
- `*either*()`
  - dependency either on the previous stage, or that one before it
  - there are **NO GUARANTEES** which will be actually used

#### By execution model
- `*async*()`
  - using default asynchronous execution
- else if `Executor` one of the method params, provided executor is used
- otherwise, default execution is used

### Catching of normal values / exceptions in stages by method
| Normal values | Exceptions | Method           |
| ------------- | ---------- |------------------|
|      ❌        |      ❌     | `whenComplete`   |
|      ❌        |      ✓     | `exceptionally`  |
|      ✓        |      ❌     | `then*`          |
|      ✓        |      ✓     | `handle`         |
