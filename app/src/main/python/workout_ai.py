import pandas as pd
from sklearn.cluster import KMeans
from os.path import dirname, join


def build_model():
    filename = join(dirname(__file__), "sample_data.csv")

    # Read data in pandas dataframe
    df = pd.read_csv(filename)

    # Remove any NULL values
    df = df.dropna()

    # Remove the routine column
    del df['routine']

    # Replace catagorical values with numerical ones
    df = encode_data(df)

    # Create KMeans model
    kmeans = KMeans(n_clusters=3)

    # Fit data to model
    kmeans.fit_predict(df)

    return kmeans


def get_group(gender, experience, goal):
    # Get model from last function
    kmeans = build_model()

    # Predict cluster of users data
    predict = kmeans.predict([[1, 2, 3]])

    # Get integer out of numpy array before returning
    return predict.item(0)


def encode_data(df):
    # Replace catagorical values with numerical ones
    cleanup_nums = {"gender": {"Male": 1, "Female": 0},
                    "experience": {"Begginer": 0, "Intermediate": 1, "Experienced": 2},
                    "goal": {"Weight-loss": 0, "Muscle-gain": 1, "Strength": 2, "Fitness": 3}}

    return df.replace(cleanup_nums)
