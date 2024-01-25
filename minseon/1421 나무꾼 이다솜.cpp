#define _CRT_SECURE_NO_WARNINGS
#include <iostream>
#include <stdio.h>

using namespace std;

int n, c, w; // 나무 n개, 자르는 비용 c, 나무 가격 w
int trees[51];
int max_val = -987654321;

long long solve(int tree_len) {
    int tree_cnt = 0;
    int tree_cut_cnt = 0;
    long long temp_ans = 0;
    for (int i = 0; i < n; i++) {
        int tmp_tree_cnt = tree_cnt;
        int tmp_tree_cut_cnt = tree_cut_cnt;

        if (trees[i] < tree_len) continue;

        if (trees[i] == tree_len) {
            tree_cnt++;
            // continue;
        }
        else if (trees[i] % tree_len == 0) {
            tree_cnt += trees[i] / tree_len;
            tree_cut_cnt += trees[i] / tree_len - 1;
        }
        else {
            tree_cnt += trees[i] / tree_len;
            tree_cut_cnt += trees[i] / tree_len;
        }

        // 수정 시작
        long long temp_cur = (long long)tree_cnt * tree_len * w - c * tree_cut_cnt;

        if (temp_ans > temp_cur) {
            tree_cnt = tmp_tree_cnt;
            tree_cut_cnt = tmp_tree_cut_cnt;
        }
        else
            temp_ans = temp_cur;
        // 수정 끝
    }

    // 우변에는 int 타입끼리 연산해서 long long으로 안해주면 overflow 발생함
    temp_ans = (long long)tree_cnt * tree_len * w - c * tree_cut_cnt;

    return temp_ans;
}


int main() {
    cin >> n >> c >> w;

    for (int i = 0; i < n; i++) {
        cin >> trees[i];
        if (max_val < trees[i]) max_val = trees[i];
    }

    long long ans = -987654321;
    for (int tree_len = 1; tree_len <= max_val; tree_len++) {
        long long ret = solve(tree_len);
        if (ret > ans) ans = ret;
    }

    printf("%lld\n", ans);
    return 0;
}