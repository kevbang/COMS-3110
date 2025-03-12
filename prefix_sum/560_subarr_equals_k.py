# Kevin Tran

def subarrayEqualsK(nums: list[int], k: int) -> int:
    result = 0
    prefix_sum = 0
    prefix_hash = {}
    prefix_hash[0] = 1

    for num in nums:
        prefix_sum += num
        if prefix_sum - k in prefix_hash:
           result += prefix_hash[prefix_sum - k]
        
        if prefix_sum not in prefix_hash:
            prefix_hash[prefix_sum] = 1
        else:
            prefix_hash[prefix_sum] += 1


    return result



# nums = [1, 1, 1]
# k = 2

# nums = [1, 2, 3]
k = 3
#       1  3  4  6  7
nums = [1, 2, 1, 2, 1]

print(f"Input: nums = {nums}, k = {k}")

print(f"Output: {subarrayEqualsK(nums, k)}")
