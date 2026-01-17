// take i = 0;
//      j = gap;
// if A[i] > A[j] then swap .. do i++ , j++;
// reduce gap by 2 then do the same.
void fun(){

    int n;
    cin >> n;

    vector<int> A1(n);
    for(int i  = 0 ;i < n ;i++){
        cin >> A1[i];
    }

    int gap = (n)/2 + (n)%2;

    while(true){
        int i = 0;
        int j = gap;

        while(j < n){
            int a = A1[i];
            int b = A1[j];
            if(a > b){
                swap(A1[i],A1[j]);
            }
            i++;
            j++;
        }
        if(gap == 1) break;
        gap = (gap/2) + (gap%2);

    }
    cout << A1;
    return;
}
