# Movie Recommendation System

## Overview
This project implements a **Java-based Movie Recommendation System** that suggests movies to users using three approaches:
- **Content-Based Filtering**
- **Collaborative Filtering**
- **Hybrid Recommendation (Content + Collaborative)**

The system demonstrates how recommendation engines work using **cosine similarity**, **user behavior analysis**, and **algorithmic trade-offs**, without relying on external ML libraries.

---

## Features
- Content-based movie recommendations using genre similarity
- User-based collaborative filtering using rating patterns
- Hybrid recommendation combining both approaches
- Cosine similarity for measuring similarity between movies and users
- Modular, object-oriented design for clarity and extensibility

---

## Recommendation Approaches

### 1. Content-Based Filtering
- Recommends movies based on similarity to a movie the user likes
- Uses movie genres as features
- Converts each movie into a binary genre vector
- Computes similarity using cosine similarity

**Example:**  
If a user likes *Inception*, the system recommends movies with similar genres such as *The Matrix*.

---

### 2. Collaborative Filtering
- Recommends movies based on users with similar rating behavior
- Compares users by their ratings on common movies
- Suggests movies liked by similar users that the target user hasn’t seen

**Example:**  
If two users rate the same movies similarly, movies liked by one user are recommended to the other.

---

### 3. Hybrid Recommendation
- Combines content-based and collaborative recommendations
- Returns the union of both recommendation sets
- Helps balance personalization and discovery
- Reduces limitations of using a single approach

---

## Algorithms Used
- **Cosine Similarity**
  - Measures similarity between vectors (movie genres or user ratings)
  - Focuses on overlap in patterns rather than absolute values

---

## Project Structure

## Project Structure

- **Main.java**
  - Entry point of the application
  - Loads movie and rating data
  - Demonstrates content-based, collaborative, and hybrid recommendations

- **Movie.java**
  - Represents a movie entity
  - Stores movie ID, title, and genre information

- **User.java**
  - Represents a user entity
  - Stores user ID and basic user details (used for collaborative filtering)

- **DataLoader.java**
  - Loads sample movie and user rating data
  - Provides in-memory datasets for testing recommendation logic

- **Similarity.java**
  - Implements cosine similarity
  - Used to measure similarity between movie genre vectors and user rating vectors

- **ContentBasedRecommender.java**
  - Implements content-based filtering
  - Converts movie genres into feature vectors
  - Recommends movies based on genre similarity

- **CollaborativeRecommender.java**
  - Implements user-based collaborative filtering
  - Computes similarity between users using rating patterns
  - Recommends movies liked by similar users

- **HybridRecommender.java**
  - Combines content-based and collaborative recommendations
  - Produces final recommendations using both similarity signals

- **README.md**
  - Project documentation
  - Describes system design,

---

## How to Run
1. Ensure Java (JDK 17 or higher) is installed.
2. Compile all files:
   ```bash
   javac *.java
3. Run the program:
   java Main
