
fn main() {

    let mut lista: Vec<i32> = vec![12, 32, -5, 99, 4, 0, 9, 5, -1, 3];

    println!("Lista original: {:?}", lista);

    sort(&mut lista);

    println!("Lista ordenada: {:?}", lista);
}


fn merge(left: &mut Vec<i32>, start: usize, mid: usize, end: usize, right: &mut Vec<i32>) {

    let mut pointer1 = start;
    let mut pointer2 = mid;

    for i in start..end {

        if (pointer1 < mid) && (pointer2 >= end || left[pointer1] <= left[pointer2]) {
            right[i] = left[pointer1];
            pointer1 += 1;

        } else {
            right[i] = left[pointer2];
            pointer2 += 1;
        }
    }
}

fn merge_copy(left: &mut Vec<i32>, start: usize, end: usize, right: &mut Vec<i32>) {

    for i in start..end {
        left[i] = right[i]
    }
}

fn merge_split(left: &mut Vec<i32>, start: usize, end: usize, right: &mut Vec<i32>) {

    if (end - start) <= 1 {
        return;

    } else {

        let mid: usize = (start + end)/2;

        merge_split(left, start, mid, right);
        merge_split(left, mid, end, right);

        merge(left, start, mid, end, right);

        merge_copy(left, start, end, right);
    }
}

pub fn sort(lista: &mut Vec<i32>) {

    let size: usize = lista.len();
    let mut worker: Vec<i32> = vec![0; size];

    merge_split(lista, 0, size, &mut worker);
}
