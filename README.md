<<<<<<< HEAD
# Movie Recommendation System — MovieLens 100K

An evaluated recommender system built on the real **MovieLens 100K** dataset
(100,000 ratings, 943 users, 1,682 movies). It started as a small Java prototype
(content-based + collaborative + hybrid, on a handful of hardcoded movies) and was
rebuilt in Python with a proper train/test evaluation, baseline ladder, and matrix
factorization.

## Results (held-out test set, 80/20 split, `random_state=42`)

| Model | Test RMSE | vs. global mean |
|---|---|---|
| Global mean | 1.124 | — |
| Bias model (regularized) | 0.943 | 16% lower |
| User-based CF (cosine, k=40) | 0.934 | 17% lower |
| **Matrix Factorization (Funk SVD)** | **0.922** | **18% lower** |

**Top-N ranking:** matrix factorization gives precision@10 0.070 / recall@10 0.048,
versus a popularity baseline at 0.136 / 0.137. A rating-optimized model losing to
popularity on top-N is a known effect on MovieLens; the notebook explains why and
points to ranking-aware methods (BPR, implicit ALS) as the principled next step.

## What's inside

```
movie_recommendation_mlss.ipynb   # the main deliverable — run this
data/                             # MovieLens 100K (bundled, no download needed)
legacy_java/                      # the original Java prototype this was built from
requirements.txt
```

The notebook covers: data loading, EDA, a leakage-free split, three rating-prediction
baselines, the original content-based + hybrid approaches (ported from the Java),
Funk SVD matrix factorization, an RMSE comparison, and an honest top-N ranking analysis.

## Run it

```bash
pip install -r requirements.txt
jupyter notebook movie_recommendation_mlss.ipynb   # then "Run All"
```

Pure NumPy / pandas / scikit-learn — no scikit-surprise, XGBoost, or LightGBM, so there
are no native-build / libomp dependencies. Runs end to end in under a minute. The data
loads via a relative path (`data/u.data`), so keep the `data/` folder next to the notebook.

## Data

MovieLens 100K, GroupLens Research, University of Minnesota.
F. M. Harper and J. A. Konstan. *The MovieLens Datasets: History and Context.*
ACM Transactions on Interactive Intelligent Systems, 2015.
