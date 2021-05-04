import pandas as pd
from sklearn.cluster import KMeans
from os.path import dirname, join

def get_group():
    filename = join(dirname(__file__), "sample_data.csv")

    df = pd.read_csv(filename)

    #user_df = pd.DataFrame.from_records([{'gender': 1, 'experience': 2, 'goal': 3}])

    cleanup_nums = {"gender": {"Male": 1, "Female": 0},
                    "experience": {"Begginer": 0, "Intermediate": 1, "Experienced": 2},
                    "goal": {"Weight-loss": 0, "Muscle-gain": 1, "Strength": 2, "Fitness": 3}}

    df = df.replace(cleanup_nums)

    #user_df = user_df.replace(cleanup_nums)

    new_df = df[['gender', 'experience', 'goal']].copy()

    kmeans = KMeans(n_clusters=3)

    kmeans.fit_predict(new_df[['gender', 'experience', 'goal']])

    new_df['clusters'] = y

    sns.scatterplot(x="experience", y="goal", hue=new_df['clusters'], data=new_df)
    plt.title('K-means Clustering with 2 dimensions')
    plt.show()

    #predict = kmeans.predict(user_df)

    return predict.item(0)

print(get_group())