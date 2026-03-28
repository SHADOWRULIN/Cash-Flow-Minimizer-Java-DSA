# 💸 Cash Flow Minimizer
### Data Structures & Algorithms | University Milestone Project (UIT)

The **Cash Flow Minimizer** is a specialized financial utility that reduces the complexity of group debts. By analyzing "who owes whom," the system eliminates redundant transactions and provides the most efficient path to settle all accounts using a **Greedy Algorithmic approach**.

---

## 🧠 The Algorithm (Greedy Approach)
This project solves the "Splitwise" problem by calculating the net amount for every individual:
1. **Net Amount Calculation:** `(Total Received - Total Paid)`. 
2. **Classification:** Individuals are separated into **Debtors** (negative net) and **Creditors** (positive net).
3. **Greedy Pairing:** Using a **Max-Heap (PriorityQueue)**, the system repeatedly settles the maximum debtor with the maximum creditor until all debts are zero.

---

## 🚀 Key Features
- **Dynamic Input:** Add multiple transactions between different people with varied amounts.
- **Real-time Minimization:** Instantly calculates the minimum number of transactions required.
- **Intuitive GUI:** Built with Java Swing `GridBagLayout` for a clean, organized user interface.
- **Transaction History:** View your raw input list before running the minimization logic.

---

## 🏗️ Technical Implementation
- **Data Structures:** - `HashMap`: To store and update net amounts for each person.
  - `PriorityQueue`: To efficiently fetch the largest creditors and debtors for settlement.
  - `ArrayList`: To manage the transaction history.
- **GUI Framework:** Java Swing (BorderLayout, GridBagLayout).

---

## 📂 Project Structure
```text
.
├── CashFlowMinimizerGUI.java  # Core Logic and GUI implementation
├── HomePage.java              # Application Landing Page
├── HowItWorksPage.java        # Documentation/Help screen
└── Project_Report.pdf         # Detailed DSA analysis and testing
```

---

## ⚙️ How to Run
1. **Clone the Repo:**
   
   ```bash
   git clone https://github.com/SHADOWRULIN/Cash-Flow-Minimizer-Java-DSA.git
   ```
2. **Compile:** Ensure you have the JDK installed.
   
   ```bash
   javac CashFlowMinimizerGUI.java
   ```
3. **Execute:**
   
   ```bash
   java CashFlowMinimizerGUI
   ```

---

## 👤 Author
**Muhammad Fahaz Khan**
*Computer Science Student at UIT University*

- **GitHub:** [@SHADOWRULIN](https://github.com/SHADOWRULIN)
- **LinkedIn:** [Fahaz Khan](https://www.linkedin.com/in/muhammad-fahaz-khan-85b805293/)

---

## 📄 License
This project is licensed under the **MIT License**.
