import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
from sklearn.decomposition import PCA
from sklearn.cluster import KMeans
from os.path import dirname, join

filename = join(dirname(__file__), "sample_data.csv")

df = pd.read_csv(filename)

del df[3]

def encode_gender(gender):
    if gender == "Male":
        return 0
    if gender == "Female":
        return 1

def encode_experience(experience):
    if experience == "Begginer":
        return 0
    if experience == "Intermediate":
        return 1
    if experience == "Experienced":
        return 2

def encode_goal(goal):
    if goal == "Fitness":
        return 0
    if goal == "Weight-loss":
        return 1
    if goal == "Muscle-gain":
        return 2
    if goal == "Strength":
        return 3

print(df.isnull().sum())

df[0] = df[0].apply(encode_gender)

df[1] = df[1].apply(encode_experience)

df[2] = df[2].apply(encode_goal)

print(df.head())

km = KMeans(n_clusters=3)

#y = km.fit_predict(df)

#df['cluster'] = y

#print(df.head())