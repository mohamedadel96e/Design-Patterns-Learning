# Observer Design Pattern

## Pattern Type
Behavioral Design Pattern

## Real-World Challenge

### The Problem: Stock Market Monitoring System

Imagine you are building a financial trading platform where stock prices change frequently and multiple components need to react to these changes in real-time:

- Mobile App Display: Shows current prices to traders on their phones
- Desktop Trading Dashboard: Displays real-time charts and price updates
- Email Alert System: Sends alerts when prices cross certain thresholds
- SMS Notification Service: Sends critical price alerts via text message
- Trading Bot: Automatically executes trades based on price movements
- Analytics Engine: Logs price data for historical analysis
- Risk Management System: Monitors portfolio exposure based on current prices

### Why This Is Challenging

1. Tight Coupling: If the stock price tracker directly calls each component, adding a new observer requires modifying the core stock tracking code.
2. Scalability Issues: As the number of observers grows, the stock tracker becomes bloated with update logic.
3. Maintenance Nightmare: Any change in how updates are delivered requires changes in multiple places.
4. No Flexibility: Cannot easily add or remove observers at runtime.
5. Violation of Open-Closed Principle: The system is not open for extension but closed for modification.

### Without Observer Pattern

```java
public class StockTracker {
    private EmailAlertSystem emailSystem;
    private SMSService smsService;
    private TradingBot tradingBot;
    private MobileApp mobileApp;
    // ... more dependencies
    
    public void updatePrice(Stock stock, double newPrice) {
        stock.setPrice(newPrice);
        
        // Tightly coupled to all components
        emailSystem.sendAlert(stock, newPrice);
        smsService.sendSMS(stock, newPrice);
        tradingBot.checkTrades(stock, newPrice);
        mobileApp.updateDisplay(stock, newPrice);
        // Adding a new component? Modify this method!
    }
}
```

Problems:
- Every new observer requires code changes in `StockTracker`.
- Cannot dynamically subscribe or unsubscribe observers.
- Hard to test individual components.
- Violates Single Responsibility Principle.

---

## The Observer Pattern Solution

### Definition
The Observer pattern defines a one-to-many dependency between objects so that when one object (Subject) changes state, all its dependents (Observers) are automatically notified and updated.

### Key Components

1. Subject (Observable): 
   - Maintains a list of observers.
   - Provides methods to attach and detach observers.
   - Notifies all observers when state changes.

2. Observer:
   - Defines an update interface for objects that should be notified.
   - Each concrete observer implements how it reacts to updates.

3. Concrete Subject:
   - Stores the state of interest.
   - Sends notifications when state changes.

4. Concrete Observer:
   - Implements the Observer interface.
   - Maintains reference to Subject if needed.
   - Updates its state to stay consistent with Subject.

### UML Structure

```
┌─────────────────────┐
│  <<interface>>      │
│     Subject         │
├─────────────────────┤
│ + attach(Observer)  │
│ + detach(Observer)  │
│ + notify()          │
└──────────┬──────────┘
           │
           │ implements
           ▼
┌─────────────────────┐         ┌─────────────────────┐
│  ConcreteSubject    │         │  <<interface>>      │
├─────────────────────┤         │     Observer        │
│ - state            │◆────────│ + update()          │
│ - observers: List  │  many    └──────────┬──────────┘
├─────────────────────┤                     │
│ + getState()       │                     │ implements
│ + setState()       │                     ▼
└─────────────────────┘         ┌─────────────────────┐
                                │  ConcreteObserver   │
                                ├─────────────────────┤
                                │ - observerState     │
                                ├─────────────────────┤
                                │ + update()          │
                                └─────────────────────┘
```

### How It Works

1. Subject maintains a list of all registered observers.
2. When Subject's state changes, it automatically notifies all observers.
3. Each observer pulls or receives the updated data and reacts accordingly.
4. Observers can be added or removed dynamically at runtime.
5. Subject and Observers are loosely coupled - Subject does not know concrete observer types.

### Benefits

- Loose Coupling: Subject only knows about the Observer interface, not concrete implementations.
- Open-Closed Principle: Add new observers without modifying existing code.
- Dynamic Relationships: Subscribe and unsubscribe at runtime.
- Broadcast Communication: One notification reaches all interested parties.
- Scalability: Easy to add unlimited observers.
- Testability: Mock observers for testing Subject behavior.

### Drawbacks

- Memory Leaks: Forgetting to unsubscribe can cause memory leaks.
- Update Overhead: Notifying many observers can be expensive.
- Unpredictable Order: No guaranteed order of notifications.
- Cascading Updates: Observers updating other subjects can cause chains.

---

## Implementation in Our Stock Trading System

### Before vs After

Before: StockTracker directly depends on all components.
After: StockTracker (Subject) only knows about StockObserver interface.

### Key Classes

1. StockObserver (Interface): Defines `update()` method all observers implement.
2. Stock (Concrete Subject): Maintains stock data and notifies observers on price changes.
3. Concrete Observers: MobileAppDisplay, EmailAlertSystem, TradingBot, etc.

### Push vs Pull Model

Push Model (Used in our example): Subject sends data to observers.
```java
void update(Stock stock, double oldPrice, double newPrice);
```

Pull Model: Observers query Subject for data.
```java
void update(Stock stock);
// Observer then calls: stock.getPrice()
```

---

## When to Use Observer Pattern

Use When:
- An object state change requires notifying multiple objects.
- You don't know in advance how many objects need notification.
- Observers should be loosely coupled to the subject.
- You need to add or remove observers dynamically.

Avoid When:
- Simple one-to-one relationships (no need for pattern overhead).
- Observer execution order matters (Observer doesn't guarantee order).
- Tight synchronization needed between subject and observers.
- Performance is critical and you have many observers.

---

## Real-World Examples

1. GUI Event Systems: Button clicks notify multiple listeners.
2. Model-View-Controller (MVC): Model notifies multiple Views.
3. News Feeds: Subscribers get notified of new content.
4. Stock Market: Price changes notify multiple displays or systems.
5. Weather Stations: Sensor data updates multiple displays.
6. Social Media: Followers notified when someone posts.
7. Logging Systems: Log events notify multiple log handlers.

---

## Related Patterns

- Mediator: Both define relationships, but Mediator centralizes communication while Observer distributes it.
- Singleton: Often used for the Subject to ensure one source of truth.
- Event Bus: Modern implementation of Observer with decoupling via events.

---

## Modern Alternatives

In modern systems, consider:
- Reactive Programming (RxJava, Project Reactor).
- Event-Driven Architecture (Kafka, RabbitMQ).
- Pub/Sub Systems (Redis Pub/Sub, Cloud Pub/Sub).
- Java's built-in: `java.util.Observable` (deprecated) - Use `PropertyChangeListener` or reactive libraries.

---

## Code Organization

before: Shows the tightly coupled approach without Observer pattern.
after: Shows the loosely coupled solution using Observer pattern.

Run the examples to see how the Observer pattern enables dynamic, scalable, and maintainable notification systems.
