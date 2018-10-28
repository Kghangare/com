from numpy import Infinity

def alphabeta(position,depth,maximizingplayer,alpha,beta):
    children=[position*2,(position*2)+1]
    
    if depth==0:
        return scores[position]
    
    if maximizingplayer:
        max_val=-Infinity
        for child in children:
            eval=alphabeta(child, depth-1,False, alpha, beta)
            max_val=max(max_val,eval)
            alpha=max(alpha,eval)
            if beta<=alpha:
                break
        return max_val
    else:
        min_val=+Infinity
        for child in children:
            eval=alphabeta(child, depth-1, True, alpha, beta)
            min_val=min(min_val,eval)
            beta=min(beta,eval)
            if beta<=alpha:
                break
        return min_val
    
# Driver code 
scores = [3, 5, 2, 9, 12, 5, 23, 23]
    
print alphabeta(0, 3, True, -Infinity, +Infinity)
    
    
    
    
    
    
    
    
# Driver code 
scores = [3, 5, 2, 9, 12, 5, 23, 23]
 