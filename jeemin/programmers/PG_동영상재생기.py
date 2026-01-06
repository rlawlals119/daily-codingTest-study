def solution(video_len, pos, op_start, op_end, commands):
    answer = ''
    def timeToSec(time):
        time = time.split(":")
        sec = int (time[0]) * 60 + int (time[1])
        return sec
    def secToTime(sec):
        minute = sec // 60
        sec %= 60
        time = ""
        if minute < 10: time += "0"
        time += str(minute) + ":"
        if sec < 10: time += "0"
        time += str(sec)
        return time
    
    # 시간 -> 초로 변환
    video_len = timeToSec(video_len)
    pos = timeToSec(pos)
    op_start = timeToSec(op_start)
    op_end = timeToSec(op_end)
    
    time = pos
    if time >= op_start and time <= op_end:
            time = op_end
            
    for comm in commands:
        if comm == "prev":
            if time < 10: time = 0
            else: time -= 10
        
        elif comm == "next":
            if video_len - time < 10: time = video_len
            else: time += 10
        if time >= op_start and time <= op_end:
            time = op_end
            
    answer = secToTime(time)
    
    return answer