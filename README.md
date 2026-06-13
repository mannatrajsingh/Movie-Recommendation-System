# Movie Recommendation System — MovieLens 100K

An **evaluated** recommender system on the real **MovieLens 100K** dataset (100,000 ratings, 943 users, 1,682 movies). It started as a small Java prototype (content-based + collaborative + hybrid recommendations) and was rebuilt in Python with a proper leakage-free train/test split, a baseline ladder, from-scratch matrix factorization, and an honest top-N ranking analysis.

The point of the rebuild was to move from "here are some recommendations" to "here is how well the model actually predicts, measured against baselines" — including reporting a result that complicates the headline story rather than hiding it.

![Python](https://img.shields.io/badge/Python-3.10%2B-blue)
![NumPy](https://img.shields.io/badge/NumPy-pandas-013243)
![scikit-learn](https://img.shields.io/badge/scikit--learn-cosine%20%2F%20metrics-orange)
![License](https://img.shields.io/badge/License-MIT-lightgrey)

---

## Results

Held-out test set, 80/20 split (`random_state=42`), 80,000 train / 20,000 test ratings.

### Rating prediction (RMSE — lower is better)

| Model | Test RMSE | vs. global mean |
| --- | ---: | ---: |
| Global mean | 1.124 | — |
| Bias model (regularized user/item biases) | 0.943 | 16.1% lower |
| User-based CF (cosine, k=40) | 0.934 | 16.9% lower |
| **Matrix Factorization (Funk SVD)** | **0.922** | **17.9% lower** |

### Top-N ranking — and an honest finding

| Method | Precision@10 | Recall@10 |
| --- | ---: | ---: |
| Matrix Factorization | 0.070 | 0.048 |
| Popularity baseline | **0.136** | **0.137** |

A rating-optimized model **losing to a popularity baseline on top-N** is a well-known effect on MovieLens: optimizing squared error on observed ratings is not the same objective as ranking the items a user will actually engage with, and "just recommend the globally popular titles" is a strong, if completely impersonal, baseline. The notebook surfaces this directly rather than reporting only the favorable RMSE table — and points to **ranking-aware methods (BPR, implicit ALS)** as the principled next step. The matrix-factorization model does still personalize (its top picks differ per user), which popularity by construction cannot.

---

## What's inside

```
movie_recommendation_mlss.ipynb   # the main deliverable — run this
data/                             # MovieLens 100K, bundled (no download needed)
  ├── u.data                      # 100,000 ratings (user, item, rating, timestamp)
  ├── u.item                      # movie metadata + genre flags
  ├── u.genre                     # genre list
  └── u.info                      # dataset summary
src/                              # the original Java prototype this was rebuilt from
  ├── Main.java                   # entry point (content-based / collaborative / hybrid demo)
  ├── DataLoader.java             # loads movies + ratings
  ├── ContentBasedRecommender.java
  ├── CollaborativeRecommender.java
  ├── HybridRecommender.java
  ├── Similarity.java, Movie.java, User.java
requirements.txt
```

The notebook is organized as: **load → EDA → leakage-free split → rating-prediction baselines → content-based & hybrid (ported from the Java) → Funk SVD matrix factorization → RMSE comparison → top-N ranking analysis → takeaways & next steps.**

---

## Methodology 

- **Leakage-free split.** Ratings are split 80/20 with a fixed seed; all model fitting (global mean, biases, similarities, latent factors) uses **train only**, and every metric is computed on the held-out test set.
- **A baseline ladder, not a single model.** Each model has to beat the one below it: predicting the global mean → adding regularized user/item biases → user-based collaborative filtering → matrix factorization. This shows *where* the improvement actually comes from instead of presenting one number with no context.
- **From-scratch Funk SVD.** Latent-factor model trained with per-sample SGD over user/item factors plus user/item biases and the global mean — `K=40` factors, learning rate `0.01` with `0.95` decay, L2 regularization `0.05`, 25 epochs. No `scikit-surprise`, so there's nothing to compile.
- **Two evaluation lenses.** RMSE measures rating prediction; precision@10 / recall@10 (with "relevant" = test rating ≥ 4) measure ranking. They disagree here — and the notebook treats that disagreement as the interesting result, not a footnote.

Everything runs on **pure NumPy / pandas / scikit-learn** — no `scikit-surprise`, XGBoost, or LightGBM, so there are no native-build / `libomp` dependencies. It runs end to end in under a minute.

---

## Getting started

```bash
git clone https://github.com/mannatrajsingh/Movie-Recommendation-System.git
cd Movie-Recommendation-System

pip install -r requirements.txt
jupyter notebook movie_recommendation_mlss.ipynb   # then "Run All"
```

The data loads via a relative path (`data/u.data`), so keep the `data/` folder next to the notebook.

### Run on Google Colab

[Open the notebook in Colab »](https://colab.research.google.com/github/mannatrajsingh/Movie-Recommendation-System/blob/main/movie_recommendation_mlss.ipynb)

### The original Java prototype (optional)

```bash
cd src
javac *.java
java Main
```

