#include<iostream>
#include<cstdio>
#include<algorithm>

char input[55];
int alpha[26];
int right[55];
 
int main()
{
    int cnt = 0;
    int i, j;
    int center = -1;
 
    scanf("%s", input);
 
    for (i = 0; input[i] != '\0'; i++) {
        alpha[input[i] - 'A']++;
    }
 
    for (i = 0; i < 26; i++) {
        if (alpha[i] % 2) {
            center = i;
            cnt++;
        }
    }
    if (cnt >= 2) {
        printf("I'm Sorry Hansoo\n");
        return 0;
    }
 
    cnt = 0;
    for (i = 0; i < 26; i++) {
        if (i == center) {
            alpha[i]--;
        }
 
        for (j = 0; j < alpha[i] / 2; j++) {
            printf("%c", i + 'A');
        }
        for (; j < alpha[i]; j++) {
            right[cnt++] = i;
        }
    }
    if (center >= 0)    printf("%c", center + 'A');
    for (i = cnt - 1; i >= 0; i--) {
        printf("%c", right[i] + 'A');
    }
    printf("\n");
 
    return 0;
}